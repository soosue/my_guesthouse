package com.java.guesthouse.api.oauth;

import static com.java.guesthouse.api.oauth.KakaoOAuthConstants.*;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GetUserInfoApi {
    private static final HttpMethod METHOD = HttpMethod.POST;

    private static final String PROPERTY_KEY_VALUE = """
            ["kakao_account.email","properties.nickname", "properties.profile_image"]""";

    private WebClient webClient = WebClient.builder()
            .baseUrl(KAPI_HOST)
            .defaultHeader("Content-Type", APPLICATION_FORM_URLENCODED_UTF_8_VALUE)
            .build();

    public GetUserInfoResponse request(String accessToken) {
        return webClient.method(METHOD)
                .uri(USER_ME_URI)
                .header("Authorization", AUTHORIZATION_BEARER.replace("${ACCESS_TOKEN}", accessToken))
                .body(BodyInserters.fromFormData("property_keys", PROPERTY_KEY_VALUE))
                .retrieve()
                .toEntity(GetUserInfoResponse.class)
                .block()
                .getBody();
    }
}
