package com.security1.springsecurity1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity  // Spring Security Filter가 Spring Filter Chain에 등록된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 해당 메서드의 리턴되는 Object를 Spring Container에 등록
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();  // csrf 방어 설정 비활성화
        http.authorizeRequests().antMatchers("/user/**").authenticated()    // /user/* 경로로의 접근은 인증을 받은(로그인 한) 유저만 접근 가능
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") // /manager/* 경로로의 접근은 인증을 받은(로그인 한) 유저 중, admin or manager 권한이 있는 유저만 접근 가능
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // /admin/* 경로로의 접근은 인증을 받은(로그인 한) 유저 중, admin 권한이 있는 유저만 접근 가능
                .anyRequest().permitAll()   // 위 3가지 경우가 아니면 모든 권한이 허용
                .and()
                .formLogin() // formLogin 형식
                .loginPage("/loginForm");   // Login Page의 url 설정


    }
}
