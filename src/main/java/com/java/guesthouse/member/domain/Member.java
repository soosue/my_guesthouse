package com.java.guesthouse.member.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GEN",
        sequenceName = "MEMBER_PK_SEQ",
        initialValue = 1000,
        allocationSize = 1
)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GEN")
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String info;
    private Long point = 0L;
    private String imageName;
    private String imagePath;
    private Long imageSize;
    private String memberLevel = "A";
    private LocalDateTime createdAt = LocalDateTime.now();

    public Member() {
    }

    public Member(String email, String name) {
        this(email, null, name, null);
    }

    public Member(String email, String password, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getMemberLevel() {
        return memberLevel;
    }
}
