package com.kosta.project.config;

import com.kosta.project.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailService userService;

    // 2. 리소스 접근 빈 설정
    // 시큐리티 기능 비활성화
    @Bean
    public WebSecurityCustomizer configure() {
        System.out.println(" WebSecurityConfig's configure()");
        return (web) -> web.ignoring()
                .requestMatchers("/static/**");
    }

    // 3. 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println(" WebSecurityConfig's filterChain()");
        return http
                .authorizeRequests() // 인증, 인가 설정
                .requestMatchers("/login", "/join").permitAll() //로그인 없이 접근 가능 페이지
                .anyRequest().authenticated()
                .and()
                .formLogin() // 폼 기반 로그인 설정
                .loginPage("/login")
                .defaultSuccessUrl("/mainPage")
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .and()
                .csrf().disable()    //로컬에서 확인하기 위해 csrf 비 활성화
                .build();
    }

    // 4.인증 관리자 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailService userDetailService) throws Exception {
        System.out.println(" WebSecurityConfig's authenticationManager()");
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    // 1. 비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        System.out.println("WebSecurityConfig's bCryptPasswordEncoder()");
        return new BCryptPasswordEncoder();
    }

}