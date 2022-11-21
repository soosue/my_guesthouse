package com.java.guesthouse.point.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pointaccumulate")
public class PointAccumulate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "accucode")
    private Long id;

    @Column(name = "accupoint")
    private Long point;

    @Column(name = "membercode")
    private Long memberId;

    private Long guestHouseId;

    @Column(name = "accuplace")
    private String guestHouseName;

    @Enumerated(EnumType.STRING)
    private PointType pointType;

    @Column(name = "accudate")
    private LocalDateTime createdDate;

    public PointAccumulate() {
    }

    public PointAccumulate(Long point, Long memberId, Long guestHouseId, PointType pointType) {
        this.point = point;
        this.memberId = memberId;
        this.guestHouseId = guestHouseId;
        this.pointType = pointType;
        this.createdDate = LocalDateTime.now();
    }

    public enum PointType {
        RESERVE_GUESTHOUSE, RESERVE_EXPERIENCE
    }

    public Long getId() {
        return id;
    }

    public Long getPoint() {
        return point;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getGuestHouseId() {
        return guestHouseId;
    }

    public String getGuestHouseName() {
        return guestHouseName;
    }

    public PointType getPointType() {
        return pointType;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
