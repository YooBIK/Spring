package com.security1.springsecurity1.repository;

import com.security1.springsecurity1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository는 CRUD 함수를 갖고 있음
 * @Repository 애노테이션 없어도 됨!! JpaRepository를 상속받으면 자동으로 스프링 빈으로 등록됨
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    /*
     * select * from user where username=?
     */
    public User findByUsername(String username);



}
