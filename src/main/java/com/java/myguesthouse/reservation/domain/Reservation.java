package com.java.myguesthouse.reservation.domain;

import com.java.myguesthouse.guesthouse.domain.GuestHouse;
import com.java.myguesthouse.member.domain.Member;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Reservation {

	public enum State {
		TEMPORAL, RESERVED, COMPLETED, CANCELED;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private GuestHouse guestHouse;

	@OneToOne
	private Member member;

	private LocalDate checkInDate;

	private LocalDate checkOutDate;

	private Integer guestsCount;

	private Integer totalAmount;

	private State state = State.TEMPORAL;

	private LocalDateTime createdDate;

	public Long getId() {
		return id;
	}

	public Long getHouseId() {
		return guestHouse.getId();
	}

	public Long getMemberId() {
		return member.getId();
	}

	public GuestHouse getGuestHouse() {
		return guestHouse;
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

	public Integer getTotalAmount() {
		return totalAmount;
	}
}
