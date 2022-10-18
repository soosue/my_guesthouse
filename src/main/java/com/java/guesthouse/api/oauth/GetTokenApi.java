package com.java.guesthouse.api.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GetTokenApi {
    private static final HttpMethod METHOD = HttpMethod.POST;
    private static final String HOST = "https://kauth.kakao.com";
    private static final String URI = "/oauth/token";
    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";
    private static final String GRANT_TYPE = "authorization_code";
    private static final String REDIRECT_URI = "http://localhost:8080/v1/members/kakaologin";

    @Value("${login.kakao.key.rest-api}")
    private String restApiKey;

    private WebClient webClient = WebClient.builder()
            .baseUrl(HOST)
            .defaultHeader("Content-Type", CONTENT_TYPE)
            .build();

    public GetTokenResponse request(String code) {
        return webClient.method(METHOD)
                .uri(URI)
                .body(BodyInserters
                        .fromFormData("grant_type", GRANT_TYPE)
                        .with("client_id", restApiKey)
                        .with("redirect_uri", REDIRECT_URI)
                        .with("code", code))
                .retrieve()
                .toEntity(GetTokenResponse.class)
                .block()
                .getBody();
    }
}
