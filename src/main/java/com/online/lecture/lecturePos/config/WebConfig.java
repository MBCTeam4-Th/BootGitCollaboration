package com.online.lecture.lecturePos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//사용자가 images/course/abcd.jpg 요청하면 서버가 c:/lecture/class/abcd/jpg 를 찾아서 반환해주는 다리역할
    // addResourceHanlers()에서 이매핑을 설정함
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
