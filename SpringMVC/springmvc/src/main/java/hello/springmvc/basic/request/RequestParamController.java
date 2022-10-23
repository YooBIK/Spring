package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    // 없으면 return 하는 string으로 Viewresolver를 통해 해당하는 이름의 view를 찾게됨, 이걸 쓰면 response body부분에 return 하는 값을 넣어서 반환
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName, @RequestParam("age") int memberAge) {
        log.info("username = {}, age = {}", memberName, memberAge);
        return "ok";
    }

    //HTTP Parameter 이름과 변수 이름이 같으면 name 속성 생략 가능!
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    //기본 자료형에 대해 @RequestParam 생략 가능
    //히지만, 써주는 것이 직관적으로 이해하기 편함
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    // Java에선 기본형(int, byte, char, short, long, float, double, boolean)에
    // null을 입력 할 수 없음
    // null을 int형에 넣으려면 Integer으로 선언
    // required = true 일 때, /request-param-required/username= 와 같이 빈문자가 입력되면 입력이 된 것으로 받아들임
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username, @RequestParam(required = false) Integer age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    // 사실상 defaultValue를 사용하면 required가 의미가 없음
    // 빈 문자열이 들어오더라도 defaultValue가 적용됨
    // null이 들어와도 defaultValue가 적용됨
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username, @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);
        return "ok";
    }

    //@ModelAttribute 는 생략 가능하지만 혼돈이 생길 수도 있음
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);
        return "ok";
    }


}
