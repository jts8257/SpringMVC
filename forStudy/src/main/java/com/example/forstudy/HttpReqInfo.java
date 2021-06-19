package com.example.forstudy;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class HttpReqInfo {

    public void printStartLine(HttpServletRequest req) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("method : " + req.getMethod());
        System.out.println("protocol : " + req.getProtocol());
        System.out.println("URL : " + req.getRequestURL());
        System.out.println("URI : " + req.getRequestURI());
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    public void printHeaders(HttpServletRequest req) {
        System.out.println("--- Headers - start ---");
        req.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": "
                        + req.getHeader(headerName)));
        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    public void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header조회 start ---");
        System.out.println("[Host]");
        System.out.println("request.getServerName() = " +
                request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " +
                request.getServerPort()); //Host 헤더
        System.out.println();
        System.out.println("[Accept-Language]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " +
                        locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();
        System.out.println("[cookie]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();
        System.out.println("[Content]");
        System.out.println("request.getContentType() = " +
                request.getContentType());
        System.out.println("request.getContentLength() = " +
                request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " +
                request.getCharacterEncoding());
        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }

    public void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote]");
        System.out.println("request.getRemoteHost() = " +
                request.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " +
                request.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " +
                request.getRemotePort()); //
        System.out.println();
        System.out.println("[Local]");
        System.out.println("request.getLocalName() = " +
                request.getLocalName()); //
        System.out.println("request.getLocalAddr() = " +
                request.getLocalAddr()); //
        System.out.println("request.getLocalPort() = " +
                request.getLocalPort()); //
        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }
}
