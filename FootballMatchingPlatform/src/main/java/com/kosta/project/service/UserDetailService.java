package com.kosta.project.service;

import com.kosta.project.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserDetailService implements UserDetailsService {

    // UserDetailsService 는 메모리 내의 사용자 목록을 관리
    private final List<UserDetails> users;

    public UserDetailService(List<UserDetails> users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  users.stream()
                .filter(u -> u.getUsername().equals(username))  // 사용자 목록에서 요청된 사용자 이름과 일치하는 항목 필터링
                .findFirst()  // 일치하는 사용자 반환
                .orElseThrow(() -> new UsernameNotFoundException("User not found...!"));  // 사용자 이름이 존재하지 않으면 예외 발생;
    }


}
