package com.java.guesthouse.member.service.dto;

import org.springframework.util.Assert;

public record LoginRequest(String email, String password) {
    public LoginRequest {
        Assert.notNull(email, "email cannot be null");
        Assert.notNull(password, "password cannot be null");
    }
}
