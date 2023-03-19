package com.security1.springsecurity1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

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

    @GetMapping("/user")
    public String user(){

        return "user";
    }

    @GetMapping("/admin")
    public String admin(){

        return "admin";
    }

    @GetMapping("/manager")
    public String manager(){

        return "manager";
    }

    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @GetMapping("/join")
    public String join(){

        return "join";
    }

    @GetMapping("/joinProc")
    @ResponseBody
    public String joinProc(){

        return "회원가입 완료 !!";
    }


}
