package com.java.myguesthouse.member.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Level memberLevel;
    private int point;
    private String memberInfo;

    private String memberImgName;
    private String memberImgPath;
    private long memberImgSize;

    private LocalDateTime createdDate;

    public Member() {
    }

    public Member(String name, String email, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.memberLevel = Level.MEMBER;
        this.createdDate = LocalDateTime.now();
    }

    public enum Level {
        GUEST, MEMBER, HOST, ADMIN
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        return id != null ? id.equals(member.id) : member.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
