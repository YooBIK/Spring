package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView modelAndView = new ModelAndView("/response/hello")
                .addObject("data","hello!");
        return modelAndView;
    }

    //@ResponseBody
    //이 경우 View를 찾지 않고 Response Body에 직접 문자열을 실어서 반환
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data","hello!");
        return "response/hello";
    }

    /*
    @Controller 사용 && HttpServletResponse ,OutputStream 사용 X
    -> 요청 URL을 논리 View 이름으로 사용

    **명시성이 떨어짐 -> 권장하지 않음
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data","hello!");
    }


}
