package com.java.myguesthouse.guesthouse.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer guestsCount;

    @ManyToOne
    private GuestHouse guestHouse;

	@OneToMany(mappedBy = "room" ,cascade = CascadeType.ALL)
	private List<DayOfRoom> dayOfRooms = new ArrayList<>();

    public Room() {
    }

    public Room(String name, Integer guestsCount) {
		this.name = name;
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
