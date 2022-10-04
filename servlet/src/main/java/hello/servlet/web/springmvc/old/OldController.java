package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// HandlerMapping -> 스프링 빈의 이름으로 핸들러를 찾을 수 있는 핸들러 매핑이 필요하다.
// 0 = RequestMappingHandlerMapping : 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
// 1 = BeanNameUrlHandlerMapping : 스프링 빈의 이름으로 핸들러를 찾는다.


// HandlerAdapter -> 핸들러 매핑을 통해서 찾은 핸들러를 실행할 수 있는 핸들러 어댑터를 찾고 실행해야 한다.
// 0 = RequestMappingHandlerAdapter : 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
// 1 = HttpRequestHandlerAdapter : HttpRequestHandler 처리
// 2 = SimpleControllerHandlerAdapter : Controller 인터페이스(애노테이션X, 과거에 사용) 처리
@Component("/springmvc/old-controller") // 빈 이름을 수동으로 지정
                                        // 컴포넌트 스캔: @Component를 가진 모든 대상을 가져와서 빈으로 등록하기 위해 찾는 과정
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}
