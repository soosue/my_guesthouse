package com.java.guesthouse.member.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.guesthouse.api.oauth.GetUserInfoResponse;
import com.java.guesthouse.api.oauth.KakaoOAuth;
import com.java.guesthouse.member.domain.Member;
import com.java.guesthouse.member.domain.MemberRepository;
import com.java.guesthouse.member.service.dto.KakaoLoginRequest;
import com.java.guesthouse.member.service.dto.LoginRequest;
import com.java.guesthouse.member.service.dto.MemberSaveRequest;

@Service
public class MemberService {
    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    private final MemberRepository memberRepository;

    private final KakaoOAuth kakaoOAuth;

    public MemberService(MemberRepository memberRepository, KakaoOAuth kakaoOAuth) {
        this.memberRepository = memberRepository;
        this.kakaoOAuth = kakaoOAuth;
    }

    @Transactional
    public Long saveMember(MemberSaveRequest memberSaveRequest) {
        logger.info(memberSaveRequest.toString());

        Member member = memberSaveRequest.toMember();
        memberRepository.save(member);

        return member.getId();
    }

    @Transactional(readOnly = true)
    public int checkEmail(String email) {

        return memberRepository
                .findByEmail(email)
                .isPresent() ? 1 : 0;
    }

    @Transactional(readOnly = true)
    public void login(LoginRequest loginRequest, HttpServletRequest request)
    {
        String email = loginRequest.email();
        String password = loginRequest.password();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email + "은 존재하지 않는 email입니다"));

        if (member.checkPassword(password)) {
            setSession(request.getSession(), member);
        }
    }

    private void setSession(HttpSession session, Member member) {

        session.setAttribute("memberLevel", member.getMemberLevel());
        session.setAttribute("email", member.getEmail());
        session.setAttribute("memberCode", member.getId());
    }

    @Transactional
    public void kakaoLogin(KakaoLoginRequest kakaoLoginRequest, HttpServletRequest request) {

        if (kakaoLoginRequest.isFailed()) {
            return;
        }
        String accessToken = kakaoOAuth.getToken(kakaoLoginRequest.code());
        GetUserInfoResponse userInfo = kakaoOAuth.getUserInfo(accessToken);

        String email = userInfo.email();
        String nickname = userInfo.nickname();

        Optional<Member> memberOptional = memberRepository.findByEmail(email);
        Member member = memberOptional.orElse(new Member(email, nickname));
        if (memberOptional.isEmpty()) {
            memberRepository.save(member);
        }

        HttpSession session = request.getSession();
        setSession(session, member);
        session.setAttribute("accessToken", accessToken);
    }
}
