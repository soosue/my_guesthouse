package com.java.guesthouse.review.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @Column(name = "reservecode")
    private Long id;

    @Column(name = "membercode")
    private Long memberId;

    @Column(name = "revcontent")
    private String content;

    @Column(name = "revrate")
    private Integer rate;

    @Column(name = "revdate")
    private LocalDateTime createdDate;

    public Review() {
    }

    public Review(Long reserveId, Long memberId, String content, Integer rate) {
        this.id = reserveId;
        this.memberId = memberId;
        this.content = content;
        this.rate = rate;
        this.createdDate = LocalDateTime.now();
    }
}
