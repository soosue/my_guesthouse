package com.java.myguesthouse.reservation.service;

import java.time.LocalDate;

public class ReservationSaveDto {
	private Long roomId;
	private Long memberId;

	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private Integer guestsCount;

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Integer getGuestsCount() {
		return guestsCount;
	}

	public void setGuestsCount(Integer guestsCount) {
		this.guestsCount = guestsCount;
	}
}
