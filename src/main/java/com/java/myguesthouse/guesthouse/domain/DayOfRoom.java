package com.java.myguesthouse.guesthouse.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DayOfRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate date;

	private Integer remainCount;

	@ManyToOne
	private Room room;

	public DayOfRoom() {
	}

	public DayOfRoom(LocalDate date, Integer remainCount) {
		this.date = date;
		this.remainCount = remainCount;
	}

	public static DayOfRoom today(Integer remainCount) {
		return new DayOfRoom(LocalDate.now(), remainCount);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
