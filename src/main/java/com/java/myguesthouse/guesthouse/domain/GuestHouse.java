package com.java.myguesthouse.guesthouse.domain;

import com.java.myguesthouse.member.domain.Member;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GuestHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member host;

    @OneToMany(mappedBy = "guestHouse", cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    public GuestHouse() {
    }

    public GuestHouse(Member host) {
        validate(host);
        this.host = host;
    }

    private void validate(Member host) {
        Assert.isTrue(host.isHost(), "guest house can be register only by host.");
    }

    public void addRoom(Room room) {
        room.setGuestHouse(this);
        rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
