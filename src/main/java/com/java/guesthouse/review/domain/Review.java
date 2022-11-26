package com.java.guesthouse.review.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "reserveCode")
    private Long reserveId;

    @Column(name = "memberCode")
    private Long memberId;

    @Column(name = "revContent")
    private String content;

    @Column(name = "revRate")
    private Integer rate;

    @Column(name = "revDate")
    private LocalDateTime createdDate;

    public Review() {
    }

    public Review(Long reserveId, Long memberId, String content, Integer rate) {
        this.reserveId = reserveId;
        this.memberId = memberId;
        this.content = content;
        this.rate = rate;
        this.createdDate = LocalDateTime.now();
    }
}
