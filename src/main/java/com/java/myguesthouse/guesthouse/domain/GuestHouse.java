package com.java.myguesthouse.guesthouse.domain;

import com.java.myguesthouse.member.domain.Member;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GuestHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member host;

    public GuestHouse() {
    }

    public GuestHouse(Member host) {
        validate(host);
        this.host = host;
    }

    private void validate(Member host) {
        Assert.isTrue(host.isHost(), "guest house can be register only by host.");
    }
}
