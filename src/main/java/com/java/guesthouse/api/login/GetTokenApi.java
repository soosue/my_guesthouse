package com.java.guesthouse.api.login;

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
                        .fromFormData("grant_type", "authorization_code")
                        .with("client_id", restApiKey)
                        .with("redirect_uri", "http://localhost:8080/v1/member/kakaologin")
                        .with("code", code))
                .retrieve()
                .toEntity(GetTokenResponse.class)
                .block()
                .getBody();
    }
}
