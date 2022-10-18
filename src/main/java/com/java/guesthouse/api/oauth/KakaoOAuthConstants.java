package com.java.guesthouse.api.oauth;

public class KakaoOAuthConstants {
    public static final String KAUTH_HOST = "https://kauth.kakao.com";
    public static final String KAPI_HOST = "https://kapi.kakao.com";

    public static final String OAUTH_TOKEN_URI = "/oauth/token";
    public static final String USER_ME_URI = "/v2/user/me";
    public static final String LOGOUT_URI = "/v1/user/logout";

    public static final String OAUTH_TOKEN_REDIRECT_URI = "http://localhost:8080/v1/members/kakaologin";

    public static final String AUTHORIZATION_BEARER = "Bearer ${ACCESS_TOKEN}";

    public static final String APPLICATION_FORM_URLENCODED_UTF_8_VALUE = "application/x-www-form-urlencoded;charset=utf-8";
}
