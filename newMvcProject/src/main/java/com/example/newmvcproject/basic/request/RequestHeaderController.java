package com.example.newmvcproject.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
// 어떤 값들을 받을 수 있고, 없는지 공식문서에 나와있음
// https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments
public class RequestHeaderController {
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap, // 모든 헤더를 다 가져올때
                          @RequestHeader("host") String host, // 특정 헤더값만 가져올때
                          @CookieValue(value = "myCookie", required = false)
                                  String cookie
    ) { // MultiValueMap 같은 key에 여러 value를 가질 수 있도록 함.
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale); // localeResolver라는것도 있어서 궁금하면 찾아봐라
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);
        return "ok";
    }
}