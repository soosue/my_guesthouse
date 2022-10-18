package com.java.guesthouse.api.oauth;

import static com.java.guesthouse.api.oauth.KakaoOAuthConstants.*;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class LogoutApi {
    private static final HttpMethod METHOD = HttpMethod.POST;

    private WebClient webClient = WebClient.builder()
            .baseUrl(KAPI_HOST)
            .build();

    public LogoutResponse request(String accessToken) {
        return webClient.method(METHOD)
                .uri(LOGOUT_URI)
                .header("Authorization", AUTHORIZATION_BEARER.replace("${ACCESS_TOKEN}", accessToken))
                .retrieve()
                .toEntity(LogoutResponse.class)
                .block()
                .getBody();
    }
}
