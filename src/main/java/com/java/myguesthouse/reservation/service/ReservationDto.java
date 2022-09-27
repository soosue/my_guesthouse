package com.java.myguesthouse.reservation.service;

import com.java.myguesthouse.reservation.domain.Reservation;

import java.time.LocalDate;

public class ReservationDto {
	private Long id;
	private Long guestHouseId;
	private Long memberId;

	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private Integer guestsCount;

	private String name;
	private Integer totalAmount;

	public ReservationDto(Long id, Long guestHouseId, Long memberId, LocalDate checkInDate, LocalDate checkOutDate, Integer guestsCount, String name, Integer totalAmount) {
		this.id = id;
		this.guestHouseId = guestHouseId;
		this.memberId = memberId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.guestsCount = guestsCount;
		this.name = name;
		this.totalAmount = totalAmount;
	}

	public static ReservationDto from(Reservation reservation) {
		return new ReservationDto(
			reservation.getId(),
			reservation.getRoomId(),
			reservation.getMemberId(),
			reservation.getCheckInDate(),
			reservation.getCheckOutDate(),
			reservation.getGuestsCount(),
			reservation.getRoom().getName(),
			reservation.getTotalAmount()
		);
	}

	public Long getId() {
		return id;
	}

	public Long getGuestHouseId() {
		return guestHouseId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public Integer getGuestsCount() {
		return guestsCount;
	}

	public String getName() {
		return name;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}
}
