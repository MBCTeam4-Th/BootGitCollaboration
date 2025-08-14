package com.online.lecture.lecturePos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///" + uploadDir);
        // TODO : 추후 LINUX 배포 시 /home/lecture/class로 경로 치환 필요1212
        // TODO : 그에 맞게 application.properties상의 upload-dir도 변경 필요
    }

}
