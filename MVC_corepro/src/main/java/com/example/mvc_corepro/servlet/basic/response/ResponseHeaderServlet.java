package com.example.mvc_corepro.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // http응답코드 셋팅
        // 직접 200을 넣을 수도 있지만, 가시적으로 명시해주는게 좋음.
        resp.setStatus(HttpServletResponse.SC_OK);

        // response heaedr 셋팅

        // content에 관련된 셋팅
        resp.setHeader("content-Type", "text/plain;charset=utf-8");

        // cache에 관련된 셋팅
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");

        // 나만의 헤더 랑니 생성
        resp.setHeader("my-header", "hello");

        // [Header 편의 메서드]
        // content, cookie, redirect에 관한 편의 메서드 활용
        content(resp);
        cookie(resp);
        redirect(resp);

        // [message Body]
        // text로 응답하는 방법
        resp.getWriter().write("ok");

    }

    private void content(HttpServletResponse resp) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse resp) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        resp.addCookie(cookie);
    }

    private void redirect(HttpServletResponse resp) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        resp.sendRedirect("/basic/hello-form.html");
    }
}
