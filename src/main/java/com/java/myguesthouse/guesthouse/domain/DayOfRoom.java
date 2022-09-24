package com.java.myguesthouse.guesthouse.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DayOfRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate date;

	@ManyToOne
	private Room room;

	public DayOfRoom() {
	}

	public DayOfRoom(LocalDate date) {
		this.date = date;
	}

	public static DayOfRoom today() {
		return new DayOfRoom(LocalDate.now());
	}

	public LocalDate getDate() {
		return date;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
