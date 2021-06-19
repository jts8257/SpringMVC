package com.example.mvc_corepro.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 *
 * 2. 동일한 파라미터 전송 가능
 * http://localhost:8080/request-param?username=hello&username=kim&age=20
 */

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");

        //getParameterNames()은 key 값들 반환
        //getParameter() 는 key에 대응되는 value값을 반환
        req.getParameterNames().asIterator().
                forEachRemaining(paramName -> System.out.println(paramName + "=" + req.getParameter(paramName)));
        System.out.println();

        System.out.println("[단일 파라미터 조회] - start");
        String username = req.getParameter("username");
        System.out.println("request.getParameter(username) = " + username);

        String age = req.getParameter("age");
        System.out.println("request.getParameter(age) = " + age);
        System.out.println();

        //req.getParameterValues 는 동일한 key 값의 value들을 반환
        System.out.println("[이름(key)이 같은 복수 파라미터 조회]");
        System.out.println("request.getParameterValues(username)");
        String[] usernames = req.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username=" + name);
        }
        resp.getWriter().write("ok");
    }
}
