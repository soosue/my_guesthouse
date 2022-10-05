package com.java.guesthouse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(20);
        registry.addResourceHandler("/image/**")
                .addResourceLocations("/resources/images/")
                .addResourceLocations("/resources/imagestorage/guesthouse/")
                .setCachePeriod(20);
        registry.addResourceHandler("/profileImg/**")
                .addResourceLocations("/resources/imagestorage/profile/")
                .setCachePeriod(20);
        registry.addResourceHandler("/ex/**")
                .addResourceLocations("/resources/imagestorage/experience/")
                .setCachePeriod(20);
        registry.addResourceHandler("/exImage/**")
                .addResourceLocations("/resources/imagestorage/experience/")
                .setCachePeriod(20);
    }
}
