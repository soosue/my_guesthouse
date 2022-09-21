package com.java.myguesthouse.guesthouse.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate day;

    private Integer guestsCount;

    @ManyToOne
    private GuestHouse guestHouse;

    public Room() {
    }

    public Room(LocalDate day, Integer guestsCount) {
        this.day = day;
        this.guestsCount = guestsCount;
    }

    public void setGuestHouse(GuestHouse guestHouse) {
        this.guestHouse = guestHouse;
    }
}
