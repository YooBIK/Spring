package com.security1.springsecurity1.auth.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    // userRequest는 Google 로그인 이후 Google로 부터 받은 유저의 정보가 담겨있다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest :" + userRequest);
        System.out.println("userRequest.getAccessToken() :" + userRequest.getAccessToken().getTokenValue());
        System.out.println("userRequest.getClientRegistration() :" + userRequest.getClientRegistration());
        System.out.println("userRequest.getClientRegistration() :" + super.loadUser(userRequest).getAttributes());
        return super.loadUser(userRequest);
    }
}
