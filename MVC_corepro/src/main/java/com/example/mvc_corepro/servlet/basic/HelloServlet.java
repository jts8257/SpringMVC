package com.example.mvc_corepro.servlet.basic;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("req = " + req);
        System.out.println("resp = " + resp);

        String userName = req.getParameter("username");
        System.out.println("username = [" + userName + "]");
        req.setAttribute("nameA", "vlaueA");
        System.out.println("req.attribute = " + req.getAttribute("nameA"));
        // response를 주기전에 셋팅
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        // 셋팅 완료

        // 문자를 보낸다.
        resp.getWriter().write("hello " + userName);
    }
}
