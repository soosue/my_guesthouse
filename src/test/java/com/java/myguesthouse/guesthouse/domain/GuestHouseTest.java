package com.java.myguesthouse.guesthouse.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.java.myguesthouse.member.MemberFixture.사장코롱이;
import static com.java.myguesthouse.member.MemberFixture.일반회원코롱;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GuestHouseTest {
    @DisplayName("게스트하우스는 사장님만 등록할 수 있다.")
    @Test
    void 게스트하우스_생성() {
        assertDoesNotThrow(() ->
                new GuestHouse("행복한 하우스", 사장코롱이)
        );
    }

    @DisplayName("게스트하우스는 일반인은 등록하지 못한다.")
    @Test
    void 게스트하우스_생성실패() {
        assertThatThrownBy(() ->
                new GuestHouse("행복한 하우스", 일반회원코롱)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("게스트하우스는 룸을 추가할 수 있다.")
    @Test
    void 룸_추가() {
        GuestHouse guestHouse = new GuestHouse("행복한 하우스", 사장코롱이);

        guestHouse.addRoom(new Room("봄날의 햇살룸", 5));

        assertThat(guestHouse.getRooms()).hasSize(1);
    }
}
