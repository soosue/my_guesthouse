package com.java.guesthouse.point.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "pointuse")
@Getter
public class PointUse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "usecode")
    private Long id;

    @Column(name = "usepoint")
    private Long point;

    @Column(name = "membercode")
    private Long memberId;

    private Long placeId;

    @Column(name = "useplace")
    private String placeName;

    @Column(name = "usedate")
    private LocalDateTime createdDate;

    public PointUse() {
    }

    public PointUse(Long point, Long memberId, Long placeId, String placeName) {
        this.point = point;
        this.memberId = memberId;
        this.placeId = placeId;
        this.placeName = placeName;
    }
}
