package com.java.myguesthouse.guesthouse.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate day;

    private Integer guestsCount;

    @ManyToOne
    private GuestHouse guestHouse;

	@OneToMany(mappedBy = "room" ,cascade = CascadeType.ALL)
	private List<DayOfRoom> dayOfRooms = new ArrayList<>();

    public Room() {
    }

    public Room(LocalDate day, Integer guestsCount) {
        this.day = day;
        this.guestsCount = guestsCount;
    }

    public void setGuestHouse(GuestHouse guestHouse) {
        this.guestHouse = guestHouse;
    }

	public void addTodayOfRoom() {
		DayOfRoom dayOfRoom = DayOfRoom.today();
		dayOfRoom.setRoom(this);

		dayOfRooms.add(dayOfRoom);
	}
}
