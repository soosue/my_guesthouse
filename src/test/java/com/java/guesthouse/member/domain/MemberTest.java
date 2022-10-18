package com.java.guesthouse.member.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemberTest {
    @Test
    void 비밀번호가_동일한지_확인한다() {
        Member korong = new Member("korong@next.step", "1234", "korong", "01012345678");

        assertThat(korong.checkPassword("1234")).isTrue();
        assertThat(korong.checkPassword("12345")).isFalse();
    }
}
