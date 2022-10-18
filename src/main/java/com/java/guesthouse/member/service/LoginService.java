package com.java.guesthouse.member.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.guesthouse.api.oauth.GetUserInfoResponse;
import com.java.guesthouse.api.oauth.KakaoOAuth;
import com.java.guesthouse.member.domain.Member;
import com.java.guesthouse.member.domain.MemberRepository;
import com.java.guesthouse.member.service.dto.KakaoLoginRequest;
import com.java.guesthouse.member.service.dto.LoginMember;
import com.java.guesthouse.member.service.dto.LoginRequest;

@Service
public class LoginService {

    private final KakaoOAuth kakaoOAuth;

    private final MemberRepository memberRepository;

    public LoginService(KakaoOAuth kakaoOAuth, MemberRepository memberRepository) {
        this.kakaoOAuth = kakaoOAuth;
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public Optional<LoginMember> login(LoginRequest loginRequest) {

        String email = loginRequest.email();
        String password = loginRequest.password();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email + "은 존재하지 않는 email입니다"));

        if (!member.checkPassword(password)) {
            member = null;
        }

        return LoginMember.optionalOf(member);
    }

    @Transactional
    public Optional<LoginMember> kakaoLogin(KakaoLoginRequest kakaoLoginRequest) {

        if (kakaoLoginRequest.isFailed()) {
            return Optional.empty();
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

        return LoginMember.optionalOf(member, accessToken);
    }

    public void logout(String accessToken) {

        if (accessToken != null) {
            kakaoOAuth.logout(accessToken);
        }
    }

}
