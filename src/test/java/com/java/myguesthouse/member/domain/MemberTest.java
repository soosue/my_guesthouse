package com.java.myguesthouse.member.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MemberTest {
    @Test
    void 멤버생성() {
        assertDoesNotThrow(() ->
                Member.memberFrom("코롱", "korong@email.com", "kororong", "010-1234-5678")
        );
    }
}