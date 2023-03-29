package com.security1.springsecurity1.config;

import com.security1.springsecurity1.auth.oauth.PrincipalOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// OAuth2.0 개념 및 작동 방식
// https://velog.io/@kimjaejung96/OAuth2.0-%EA%B0%9C%EB%85%90-%EB%B0%8F-%EC%9E%91%EB%8F%99%EB%B0%A9%EC%8B%9D
@Configuration
@EnableWebSecurity  // Spring Security Filter가 Spring Filter Chain에 등록된다.
// securedEnabled : @Secured 활성화 여부
// prePostEnabled : @PreAuthorize , @PostAuthorize 애노테이션 활성화 여부
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalOAuth2UserService principalOAuth2UserService;

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
                .loginPage("/loginForm")        // Login Page의 url 설정
                .loginProcessingUrl("/login")   // "/login" 주소가 호출되면, Spring Security 가 낚아채서 대신 로그인을 진행한다.
                .defaultSuccessUrl("/")         // loginForm -> 로그인 -> defaultUrl , 특정 주소 -> 로그인 -> 특정 주소
                .and()
                .oauth2Login() // OAuth 로그인 설정
                .loginPage("/loginForm") // 이 부분은 무슨 역할일까..?  // 구글 로그인 이후 처리 필요!! TIP. OAuth2 Client 라이브러리를 사용하면 (1).코드를 받고 (2).엑세스토큰 받고 (3).사용자 정보에 접근 하는 과정을 한번에 처리해준다.
                .userInfoEndpoint()
                .userService(principalOAuth2UserService);
    }
}
