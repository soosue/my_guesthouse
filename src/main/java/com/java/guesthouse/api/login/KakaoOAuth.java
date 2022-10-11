package com.java.guesthouse.api.login;

import org.springframework.stereotype.Component;

@Component
public class KakaoOAuth {
    private final GetTokenApi getTokenApi;
    private final GetUserInfoApi getUserInfoApi;

    public KakaoOAuth(GetTokenApi getTokenApi, GetUserInfoApi getUserInfoApi) {
        this.getTokenApi = getTokenApi;
        this.getUserInfoApi = getUserInfoApi;
    }


    public String getToken(String code) {
        GetTokenResponse response = getTokenApi.request(code);
        return response.access_token();
    }

    public GetUserInfoResponse getUserInfo(String accessToken) {
        return getUserInfoApi.request(accessToken);
    }
}
