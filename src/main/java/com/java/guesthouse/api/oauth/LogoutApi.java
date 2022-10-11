package com.java.guesthouse.api.oauth;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class LogoutApi {
    private static final HttpMethod METHOD = HttpMethod.POST;
    private static final String HOST = "https://kapi.kakao.com";
    private static final String URI = "/v1/user/logout";
    private static final String AUTHORIZATION = "Bearer ${ACCESS_TOKEN}";

    private WebClient webClient = WebClient.builder()
            .baseUrl(HOST)
            .build();

    public LogoutResponse request(String accessToken) {
        return webClient.method(METHOD)
                .uri(URI)
                .header("Authorization", AUTHORIZATION.replace("${ACCESS_TOKEN}", accessToken))
                .retrieve()
                .toEntity(LogoutResponse.class)
                .block()
                .getBody();
    }
}
