package com.java.guesthouse.member.service;

import org.springframework.stereotype.Service;

import com.java.guesthouse.api.oauth.KakaoOAuth;

@Service
public class LoginService {

    private final KakaoOAuth kakaoOAuth;

    public LoginService(KakaoOAuth kakaoOAuth) {
        this.kakaoOAuth = kakaoOAuth;
    }

    public void logout(String accessToken) {

        if (accessToken != null) {
            kakaoOAuth.logout(accessToken);
        }
    }

}
