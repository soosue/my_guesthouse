package com.java.myguesthouse.guesthouse.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class DayOfRoomTest {
	@DisplayName("배치로 매일매일 Room에 대한 DayOfRoom이 추가된다.")
	@Test
	void 당일_생성() {
		DayOfRoom today = DayOfRoom.today();

		assertThat(today.getDate()).isEqualTo(LocalDate.now());
	}
}
