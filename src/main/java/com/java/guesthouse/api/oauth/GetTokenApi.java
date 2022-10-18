package com.java.guesthouse.api.oauth;

import static com.java.guesthouse.api.oauth.KakaoOAuthConstants.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GetTokenApi {
    private static final HttpMethod METHOD = HttpMethod.POST;

    private static final String GRANT_TYPE = "authorization_code";

    @Value("${login.kakao.key.rest-api}")
    private String restApiKey;

    private WebClient webClient = WebClient.builder()
            .baseUrl(KAUTH_HOST)
            .defaultHeader("Content-Type", APPLICATION_FORM_URLENCODED_UTF_8_VALUE)
            .build();

    public GetTokenResponse request(String code) {
        return webClient.method(METHOD)
                .uri(OAUTH_TOKEN_URI)
                .body(BodyInserters
                        .fromFormData("grant_type", GRANT_TYPE)
                        .with("client_id", restApiKey)
                        .with("redirect_uri", OAUTH_TOKEN_REDIRECT_URI)
                        .with("code", code))
                .retrieve()
                .toEntity(GetTokenResponse.class)
                .block()
                .getBody();
    }
}
