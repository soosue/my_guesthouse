package com.java.guesthouse.config;

import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion) {
        Info info = new Info()
                .title("게스트하우스 API")
                .version(springdocVersion)
                .description("추가 중입니다.");

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("session", sessionScheme())
                )
                .security(List.of(session()))
                .info(info);
    }

    private SecurityScheme sessionScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.COOKIE).name("JSESSIONID");
    }

    private SecurityRequirement session() {
        return new SecurityRequirement().addList("session");
    }

    @Bean
    public GroupedOpenApi wishlistOpenApi() {
        return GroupedOpenApi.builder()
                .group("찜하기")
                .pathsToMatch("/v1/wishlists/**")
                .build();
    }

    @Bean
    public GroupedOpenApi reviewOpenApi() {
        return GroupedOpenApi.builder()
                .group("리뷰")
                .pathsToMatch("/v1/reviews/**")
                .build();
    }

    @Bean
    public GroupedOpenApi memberOpenApi() {
        return GroupedOpenApi.builder()
                .group("멤버")
                .pathsToMatch("/v1/members/**")
                .build();
    }

    @Bean
    public GroupedOpenApi pointOpenApi() {
        return GroupedOpenApi.builder()
                .group("포인트")
                .pathsToMatch("/v1/points/**")
                .build();
    }
}
