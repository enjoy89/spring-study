package hello.servlet.web.servletMVC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 서블릿을 컨트롤러로 사용하고, JSP를 뷰로 사용해서 MVC 패턴 적용
 * 모델은 내부의 저장소를 가지고 있는 HttpServletRequest 객체를 사용
 */
@WebServlet(name="mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";    // WER-INF -> 외부에서 호출이 불가능하고 컨트롤러를 통해서만 JSP 호출 가능
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);// controller에서 view로 이동할 때 쓰임
        dispatcher.forward(request, response); // servlet에서 jsp 호출
        // forward vs redirect
        // 리다이렉트는 클라이언트에 응답이 나갔다가 redirect 경로로 다시 요청한다.
        // 따라서 리다이렉트는 클라이언트가 인지할 수 있고, URL 경로도 실제로 변경된다. (실제 호출이 2번됨)
        // 포워드는 서버 내부에서 일어나는 호출이기 때문에 클라이언트가 전혀 인지하지 못한다.
    }
}
