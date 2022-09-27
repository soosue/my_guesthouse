package com.java.myguesthouse.payment;

import com.java.myguesthouse.reservation.service.KakaoPay;
import com.java.myguesthouse.reservation.service.ReadyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(profiles = {"secret"})
class KakaoPayTest {

	@Autowired
	private KakaoPay kakaoPay;

	@Test
	void readyTest() {
		String redirectUrl = kakaoPay.ready(
			new ReadyDto(1L + "", 1L + "",
			"coffee", 1, 2000, 0));

		assertThat(redirectUrl).isNotNull();
	}
}
