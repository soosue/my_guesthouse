package com.java.guesthouse.member.service.dto;

import org.springframework.util.Assert;

import com.java.guesthouse.member.domain.Member;

public record MemberSaveRequest(String email, String password, String passwordCheck, String name, String phoneNumber) {
    public MemberSaveRequest {
        Assert.notNull(email,"email cannot be null");
        Assert.notNull(password,"password cannot be null");
        Assert.notNull(passwordCheck,"passwordCheck cannot be null");
        Assert.notNull(name,"name cannot be null");
        Assert.notNull(phoneNumber,"phoneNumber cannot be null");
    }

    public Member toMember() {
        validate();
        return new Member(email, password, name, phoneNumber);
    }

    private void validate() {
        if (password.equals(passwordCheck)) {
            return;
        }

        throw new IllegalStateException("비밀번호 확인이 다릅니다");
    }
}
