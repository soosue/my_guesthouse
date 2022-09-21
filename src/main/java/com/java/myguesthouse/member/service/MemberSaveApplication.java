package com.java.myguesthouse.member.service;

import com.java.myguesthouse.member.domain.Member;

public class MemberSaveApplication {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    public MemberSaveApplication(String name, String email, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public Member toMember() {
        return Member.memberFrom(name, email, password, phoneNumber);
    }
}
