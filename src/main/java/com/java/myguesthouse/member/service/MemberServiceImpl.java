package com.java.myguesthouse.member.service;

import com.java.myguesthouse.member.domain.Member;
import com.java.myguesthouse.member.domain.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Long saveMember(MemberSaveApplication memberSaveApplication) {
        Member member = memberSaveApplication.toMember();
        memberRepository.save(member);

        return member.getId();
    }

    @Override
    public void checkEmail() {

    }

    @Override
    public void login() {

    }

    @Override
    public void kakaoLogin() {

    }
}
