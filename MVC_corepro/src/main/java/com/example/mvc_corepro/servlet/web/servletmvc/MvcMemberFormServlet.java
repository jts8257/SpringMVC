package com.example.mvc_corepro.servlet.web.servletmvc;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";

        // 컨트롤러에서 뷰로 이동할때 사용하는 것.
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        // 이걸 호출하면 진짜 서블릿에서 jsp를 호출할 수 있음.
        // 이렇게 호출했기 때문에
        // http://localhost:8080/servlet-mvc/members/new-form 로 들어오면
        // /WEB-INF/views/new-form.jsp 의 html문서가 보임.
        dispatcher.forward(req, resp);

        //왜 리다이렉트가 아니라 forward냐?
        // 리다이렉트는 웹 페이지 2번 호출함. 웹 브라우저에 응답이 나가고(url변경) --> 변경된 url로 서버에 다시 요청
        // forward는 서버 내부 호출이기 때문에 클라이언트가 인지하지 못해서서 한번 호출함

    }
}
