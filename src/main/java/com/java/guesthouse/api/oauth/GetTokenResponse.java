package com.java.guesthouse.api.oauth;

public record GetTokenResponse(
        String token_type,
        String access_token,
        String expires_in,
        String refresh_token,
        String refresh_token_expires_in) {
}
