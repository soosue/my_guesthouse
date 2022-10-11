package com.java.guesthouse.api.login;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GetUserInfoApi {
    private static final HttpMethod METHOD = HttpMethod.POST;
    private static final String HOST = "https://kapi.kakao.com";
    private static final String URI = "/v2/user/me";
    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";
    private static final String AUTHORIZATION = "Bearer ${ACCESS_TOKEN}";

    private WebClient webClient = WebClient.builder()
            .baseUrl(HOST)
            .defaultHeader("Content-Type", CONTENT_TYPE)
            .build();

    public GetUserInfoResponse request(String accessToken) {
        return webClient.method(METHOD)
                .uri(URI)
                .header("Authorization", AUTHORIZATION.replace("${ACCESS_TOKEN}", accessToken))
                .body(BodyInserters.fromFormData("property_keys", """
                        ["kakao_account.email","properties.nickname", "properties.profile_image"]"""))
                .retrieve()
                .toEntity(GetUserInfoResponse.class)
                .block()
                .getBody();
    }
}
