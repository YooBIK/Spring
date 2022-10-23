package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

//        System.out.println("name = " + name);

        //log.trace("trace log ="+name); //이 방법은 권장 X, 불필요한 작업에 리소스를 사용할 여지가 있다.

        log.trace("trace Log={}", name);
        log.debug("debug Log={}", name);
        log.info("info Log={}", name);
        log.warn("warn Log={}", name);
        log.error("error Log={}", name);

        return "ok";
    }
}
