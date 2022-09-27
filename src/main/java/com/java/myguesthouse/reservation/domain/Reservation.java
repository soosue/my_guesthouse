package com.java.myguesthouse.reservation.domain;

import com.java.myguesthouse.guesthouse.domain.Room;
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
	private Room room;
	@OneToOne
	private Member member;

	private LocalDate checkInDate;

	private LocalDate checkOutDate;

	private Integer guestsCount;

	private Integer totalAmount;

	@Enumerated(EnumType.STRING)
	private State state = State.TEMPORAL;

	private String tid;

	private LocalDateTime createdDate;

	public Reservation() {
	}

	public Reservation(Room room, Member member, LocalDate checkInDate, LocalDate checkOutDate, Integer guestsCount, Integer totalAmount) {
		this.room = room;
		this.member = member;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.guestsCount = guestsCount;
		this.totalAmount = totalAmount;
	}

	public void updateTid(String tid) {
		this.tid = tid;
	}

	public void reserved() {
		this.state = State.RESERVED;
	}

	public Long getId() {
		return id;
	}

	public Long getRoomId() {
		return room.getId();
	}

	public Long getMemberId() {
		return member.getId();
	}

	public Room getRoom() {
		return room;
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

	public String getTid() {
		return tid;
	}
}
