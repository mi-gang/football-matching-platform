package com.kosta.project.config;

import com.kosta.project.dto.UserDTO;
import com.kosta.project.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserDetailService userService;

    // 2. 리소스 접근 빈 설정, 일부 파일 시큐리티 기능 비활성화
    @Bean
    public WebSecurityCustomizer configure() {
        System.out.println("WebSecurityConfig's configure()");
        return (web) -> web.ignoring()
                .requestMatchers("/static/**", "/mobile/**");
    }

    // 3. 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) throws Exception {
        System.out.println("WebSecurityConfig's filterChain()");
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/join", "/css/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/mainPage"))
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true))
                .csrf(Customizer.withDefaults())
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    // 1. 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("WebSecurityConfig's passwordEncoder()");
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(Customizer.withDefaults())
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults());
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = new UserDTO("assu", "1234");
//        List<UserDetails> users = List.of(user);
//        return new UserDetailService(users);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//    @Bean
//    public SecurityFilterChain basicConfig (HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                //  CSRF 비활성화 대체가능 .csrf((csrf) -> csrf.disable())
//
//                .formLogin(AbstractHttpConfigurer::disable)
//                // formLogin 비활성화 대체 가능 .formLogin((formLogin) -> formLogin.disable())
//
//                // 로컬 개발일 경우 sameOrigin 사용하여 동일출처 허용
//                .httpBasic(AbstractHttpConfigurer::disable) //
//
//                //토큰 방식 활용을 위해 Session Stateless 설정
////                .sessionManagement((session) -> session
////                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//
//                .headers(headers -> headers
//                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//
//
//                //CORS withDefaults 사용 시 Bean 으로 등록된 corsConfigurationSource 을 사용합니다.
//                //.configurationSource(apiConfigurationSource())) 메서드 명으로 커스텀 가능
//                .cors(withDefaults())
//
//
//                .securityMatcher("/api/**", "/app/**")
//                .authorizeHttpRequests((authz) -> authz
//                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//
//                );
//
//
//        return http.build();
//    }


}