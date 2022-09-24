package com.java.myguesthouse.guesthouse.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RoomTest {
    @Test
    void 룸_생성() {
        assertDoesNotThrow(() ->
                new Room("봄날의 햇살룸", 5)
        );
    }

	@DisplayName("배치로 매일매일 Room에 대한 DayOfRoom이 추가된다.")
	@Test
	void 룸_당일_생성() {
		Room room = new Room("봄날의 햇살룸", 5);

		room.addTodayOfRoom();
	}
}
