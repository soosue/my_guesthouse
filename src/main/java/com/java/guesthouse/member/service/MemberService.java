package com.java.guesthouse.member.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.guesthouse.api.oauth.KakaoOAuth;
import com.java.guesthouse.member.domain.Member;
import com.java.guesthouse.member.domain.MemberRepository;
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
    public Long count() {
        return memberRepository.count();
    }

    @Transactional(readOnly = true)
    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + "존재하지 않는 id입니다"));
    }

    @Transactional(readOnly = true)
    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }
}
