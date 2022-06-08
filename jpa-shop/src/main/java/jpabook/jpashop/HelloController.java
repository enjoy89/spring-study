package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 특정 view를 사용자에게 반환
public class HelloController {

    @GetMapping("hello")    // localhost:8080/hello인 URL이 요청되면 이 메소드가 호출됨
    public String hello(Model model) {  // 컨트롤러가 UI에 있는 model에 데이터를 실어 뷰에 넘길 수 있음
        model.addAttribute("data", "hello!");
        return "hello"; // 화면 이름값을 반환 (화면에 home.html이 나타남)
    }
}
