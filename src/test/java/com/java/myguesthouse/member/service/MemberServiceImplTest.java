package com.java.myguesthouse.member.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.java.myguesthouse.member.MemberFixture.일반회원코롱신청서;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @Test
    void 멤버저장() {
        Long id = memberService.saveMember(일반회원코롱신청서);
        assertThat(id).isNotNull();
    }
}