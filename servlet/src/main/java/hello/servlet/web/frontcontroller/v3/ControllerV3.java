package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    // 서블릿을 사용하지 않음
    // 서블릿의 HttpServletRequest에서 제공하는 파라미터는 프론트 컨트롤러가 paramMap에 담아서 호출하면 된다.
    // 응답 결과로 뷰이름과 뷰에 전달할 모델 데이터를 포함하는 ModelView 객체를 반환하면 된다.
    ModelView process(Map<String, String> paramMap);
}
