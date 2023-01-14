package com.java.guesthouse.refactor.wishlist.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long memberId;

    private Long houseId;

    public Wishlist(Long memberId, Long houseId) {
        this.memberId = memberId;
        this.houseId = houseId;
    }
}
