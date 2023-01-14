package com.java.guesthouse.refactor.wishlist.domain;

import com.java.guesthouse.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private Long houseId;

    public Wishlist(Member member, Long houseId) {
        this.member = member;
        this.houseId = houseId;
    }
}
