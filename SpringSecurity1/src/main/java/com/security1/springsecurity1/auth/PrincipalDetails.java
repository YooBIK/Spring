package com.security1.springsecurity1.auth;

import com.security1.springsecurity1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Spring Security가 "/login" 주소 요청을 낚아채서 로그인을 진행한다.
 * 로그인 진행이 완룓되면 Spring Security session을 만든다. (Security Context Holder에 저장한다.)
 * 여기에 저장될 수 있는 Object 는 Authentication 타입의 객체
 * 즉, Authentication 안에 User 정보가 있어야 함
 * Authentication 객체의 내부에 담긴 User 정보는 UserDetails 객체 타입으로 저장된다.
 * Security Session(Security Context) => Authentication => UserDetails
 * 지금 작성중인 클래스는 UserDetails 인터페이스를 구현한 클래스이므로, Authentication 객체 내부의
 * UserDetails에 이 객체를 넣으면 된다!!
 */
public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 유저의 권한을 리턴하는 곳이다 !!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 사이트에 오래동안 로그인하지 않아 휴면 계정 처리하고 싶을 때 사용
    // 현재 시간 - 최근 로그인 시간 => 특정 기간보다 크면 return false;
    @Override
    public boolean isEnabled() {
        return true;
    }
}
