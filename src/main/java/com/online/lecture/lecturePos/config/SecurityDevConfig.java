package com.online.lecture.lecturePos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityDevConfig {
    // [임시 설정]
    // 관리자(CRUD)와 학생(조회) 페이지 개발·테스트를 위해 시큐리티를 잠시 모두 허용
    // 회원 담당자가 정식 로그인/권한 설정을 적용하면 이 클래스는 삭제할 것

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 개발 중 임시: CSRF off (배포 전 반드시 되돌리기)
                .csrf(csrf -> csrf.disable()) //DEV Only
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/images/**",
                                "/admin/course/**",
                                "/student/courses/**"
                        ).permitAll()
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    // todo: 관리자 CRUD+학생조회 SecurityDevConfig 추가
    // 회원 담당에서 정식 시큐리티 적용 시 이 클래스를 삭제
    // 테스트할 때만 적용
    //나중에 회원 담당자가 로그인/권한 설정(시큐리티)을 적용하면 이 설정은 지우면 됩니다.

}

