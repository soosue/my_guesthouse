package com.java.guesthouse.api.oauth;

public record GetUserInfoResponse(
        String id,
        Properties properties,
        KakaoAccount kakao_account) {

    public String email() {
        return kakao_account().email();
    }

    public String nickname() {
        return properties().nickname();
    }

    static record Properties(
            String nickname,
            String profile_image) {
    }

    static record KakaoAccount(String email) {
    }
}
