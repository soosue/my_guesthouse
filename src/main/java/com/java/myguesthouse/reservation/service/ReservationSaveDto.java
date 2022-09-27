package com.java.myguesthouse.reservation.service;

import java.time.LocalDate;

public class ReservationSaveDto {
	private Long guestHouseId;
	private Long memberId;

	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private Integer guestsCount;
}
