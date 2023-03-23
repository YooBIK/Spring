package com.security1.springsecurity1.auth;

import com.security1.springsecurity1.model.User;
import com.security1.springsecurity1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Security 설정에서 loginProcessingUrl("/login");
 * "/login" 요청이 오면, 자동으로 UserDetailsService 타입으로 Spring Container 에 등록되어 있는 (@Service 때문에 이 클래스는 등록된다.)
 * Spring Bean의 loadUserByUsername 함수가 실행된다.
 */
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Security Context에 전달될 Authentication 객체에 전달된다.
    // Security Context(Authentication(UserDetails(userEntity))) 이런 개념
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        if(userEntity != null){
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
