package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * FrontController 패턴 특징
 * 1. 프론트 컨트롤러 서블릿 하나로 클라이언트의 요청을 받음
 * 2. 프론트 컨트롤러가 요청에 맞는 컨트롤러를 찾아서 호출
 * 3. 프론트 컨트롤러를 제외한 나머지 컨트롤러는 서블릿을 사용하지 않아도 됨
 * 4. 모든 입구는 하나로 정리 -> 공통 처리 가능
 */
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")    // 하위 모든 요청은 이 서블릿에서 받아들임
public class FrontControllerServletV1 extends HttpServlet {

    // key -> 매핑 URI
    // value -> 호출될 Controller
    private Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    public FrontControllerServletV1() {
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();

        // requestURI를 조회해서 실제 호출할 컨트롤러를 먼저 찾는다
        // 해당 컨트롤러가 없으면 404 상태 코드를 반환하고,
        // 해당 컨트롤러가 있으면 해당 컨트롤러를 실행한다.
        ControllerV1 controller = controllerV1Map.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        } else {
            controller.process(request, response);
        }
    }
}
