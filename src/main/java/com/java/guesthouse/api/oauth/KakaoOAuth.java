package com.java.guesthouse.api.oauth;

import org.springframework.stereotype.Component;

@Component
public class KakaoOAuth {
    private final GetTokenApi getTokenApi;
    private final GetUserInfoApi getUserInfoApi;
    private final LogoutApi logoutApi;

    public KakaoOAuth(GetTokenApi getTokenApi, GetUserInfoApi getUserInfoApi, LogoutApi logoutApi) {
        this.getTokenApi = getTokenApi;
        this.getUserInfoApi = getUserInfoApi;
        this.logoutApi = logoutApi;
    }

    public String getToken(String code) {
        GetTokenResponse response = getTokenApi.request(code);
        return response.access_token();
    }

    public GetUserInfoResponse getUserInfo(String accessToken) {
        return getUserInfoApi.request(accessToken);
    }

    public LogoutResponse logout(String accessToken) {
        return logoutApi.request(accessToken);
    }
}
