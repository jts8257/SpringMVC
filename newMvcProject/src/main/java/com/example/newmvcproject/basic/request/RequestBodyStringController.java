package com.example.newmvcproject.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletInputStream inputStream = req.getInputStream();

        // Java는 바이트 코드로 데이러를 읽어들이기 때문에
        // 바이트 코드를 어떻게 인코딩 할지 꼭꼭 명시해 줘야한다.
       String messageBody =  StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        resp.getWriter().write("OK");
    }

    // 굳이 서블릿 전체가 필요한가?
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer respWriter) throws IOException {

        String messageBody =  StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        respWriter.write("OK");
    }

    // 스트림으로 받는것도 귀찮다.
    // HttpEntity라는게 있다. header와 body정보를 직접 조회할 수 있게 해줌.
    // 요청 파라미터를 조회하는 기능과 관계 없음.
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {

        // HttpMessage Converter가 동작함.
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("OK");
    }

    // 요청은 RequestBody로 받고
    // returnt 은 ResponseBody로 body에 꽂아주고.
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV3(@RequestBody String messageBody) {
        // header 정보를 받아오고 싶으면 RequestHeader 를 추가하면 됨.
        log.info("messageBody={}" ,messageBody);

        return "OK";
    }

}
