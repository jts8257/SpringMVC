package com.example.newmvcproject.basic.request;

import com.example.newmvcproject.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 이하의 경우는 Get, Post의 경우에만 사용한다.
 * 직접 HttpBody, Header 를 읽어야하는 경우는 RequestBodyString 에서 HttpEntity를 참고하자.
 */

@Slf4j
@Controller
public class RequestParamController {
    /**
     * 반환 타입이 없으면서 이렇게 응답에 값을 직접 집어넣으면, view 조회X
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("username={},age={}", memberName,memberAge);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    //parameter key와 변수명이 동일하고, 그 타입이 단순한 타입 (int, String)등이라면 다 생략할 수 있다.
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // null과 "" 는 다르고, int형에는 null을 넣을 수 없으므로 Inteager를 쓰는게 좋다.
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // default값이 들어가면 사실 required는 필요가 없음.
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "geust") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap){
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        // 모델 객채가 생성되고, 요청 파라미터의 값도 모두 들어가 있다.
        // 요청 파라미터의 이름으로  setter, getter를 찾는다.
        // 입력값이 String, int 등에 맞지않게 들어오면 BindException 이 발생한다.
        log.info("helloData = {}", helloData);
        return "OK";
    }

    // 이러한 ModelAttribute 생략할 수 있다.
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {

        log.info("helloData = {}", helloData);
        return "OK";
    }
    /**
     * RequestParam과 ModelAttribute 모두 생략할 수 있으면 스프링은 어떤걸 기준으로 매핑하는거지?
     * (1) String, int, Integer같은 단순 타입은 RequestParam...
     * 나머지는 ModelAttribute (그래서 위는 객체 매핑인것이다.)
     * 만약 ArgumentResolver로 지정되어 있으면 ModelAttribute를 따르지 않는다.
     */
}