package com.security1.springsecurity1.controller;

import com.security1.springsecurity1.model.User;
import com.security1.springsecurity1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 권한 설정을 통해 각 페이지마다 권한이 있는 사용자만 접근할 수 있도록 하자 !!
 */
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Login 한 사용자만 조회할 수 있도록 하고자 한다.
     */
    @GetMapping({"","/"})
    public String index(){
        // Mustache 사용
        // 기본 폴더 위치 : src/main/resources/
        /**
         *  뷰 리졸버 설정
         *  - prefix : templates/
         *  - subfix : .mustache
         *  application.yml (application.properties)에 적어주면 됨!!
         *      build.gradle (or POM.xml)의 dependency에 mustache를 등록하면 생략 가능
          */
        return "index";
    }

    /**
     * Login 한 사용자만 조회할 수 있도록 하고자 한다.
     */
    @GetMapping("/user")
    @ResponseBody
    public String user(){

        return "user";
    }

    /**
     * Login 한 사용자 중, Admin 권한이 있는 사용자만 조회할 수 있도록 하고자 한다.
     */
    @GetMapping("/admin")
    @ResponseBody
    public String admin(){

        return "admin";
    }

    /**
     * Login 한 사용자 중, Manager 권한이 있는 사용자만 조회할 수 있도록 하고자 한다.
     */
    @GetMapping("/manager")
    @ResponseBody
    public String manager(){

        return "manager";
    }

    // 별도의 설정이 없으면 , Spring Security가 낚아챔 -> SecurityConfig 파일 생성 후 더이상 낚아채지 않음
    @GetMapping("/loginForm")
    public String loginForm(){

        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){

        return "joinForm";
    }

    @PostMapping("/join")
    public String join(User user){
        System.out.println(user);
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);  // 회원 가입 성공 BUT 비밀번호가 암호화 되어있지 않아 Spring Security로 Login 할 수 없음
        return "redirect:/loginForm";
    }

    @GetMapping("/info")
    @ResponseBody
    @Secured("ROLE_ADMIN")  // 특정 메서드에 접근할 수 있는 권한을 설정
    public String info(){
        return "개인정보";
    }

    @GetMapping("/data")
    @ResponseBody
    // 메서드 호출 권한 설정 , 메서드 호출 직전에 권한을 체크한다.
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public String data(){
        return "데이터 정보";
    }



}
