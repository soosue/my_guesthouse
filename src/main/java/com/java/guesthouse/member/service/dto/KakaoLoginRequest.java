package com.java.guesthouse.member.service.dto;

public record KakaoLoginRequest(
        String code,
        String state,
        String error,
        String error_description) {

    public boolean isFailed() {
        return error != null;
    }
}
