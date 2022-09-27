package com.java.myguesthouse.reservation.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
	private final KakaoPay kakaoPay;

	public PaymentService(KakaoPay kakaoPay) {
		this.kakaoPay = kakaoPay;
	}

	public String payByKakaoPay(ReservationDto reservationDto) {
		return kakaoPay.ready(new ReadyDto(
			reservationDto.getId() + "",
			reservationDto.getMemberId() + "",
			reservationDto.getName(), 1, reservationDto.getTotalAmount(), 0));
	}
}
