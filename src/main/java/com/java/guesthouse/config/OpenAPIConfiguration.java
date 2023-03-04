package com.java.guesthouse.config;

import java.util.List;

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


}
