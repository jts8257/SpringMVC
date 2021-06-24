package com.example.newmvcproject.basic.request;

import com.example.newmvcproject.basic.HelloData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Http Header에 conntente-type 이 application/json 인지 확인해야한다.
 **/
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    // @PostMapping("/request-body-json-v1") 이거는 서블릿에서 하던 방식

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyStringV2(@RequestBody String messageBody) throws JsonProcessingException {

        /* postMan 에서 다음과 같이 데이터 쏘기
         *  {"username":"hello","age":"28"}
         */

        log.info("messageBody={}" ,messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }

    /* 굳이 ObjectMapper까지 매칭시켜야 하나?
    * @RequestBody 가 json 으로 넘어온 데이터를 Object로 자동 매핑해주기 때문에 가능.
    * */

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyStringV3(@RequestBody HelloData helloData) throws JsonProcessingException {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "OK";
        /**
         * 만약 여기서 @RequestBody 를 제거하면, ModelAttribute가 생략된걸로 인식되어
         * Http Body에서 데이터를 가져오는게 아니라 요청 파라미터를 처리하는 것으로 인식한다.
         * =======================이하 해당 규칙 =======================
         * 스프링은 @ModelAttribute , @RequestParam 해당 생략시 다음과 같은 규칙을 적용한다.
         * String , int , Integer 같은 단순 타입 = @RequestParam이 생략된걸로 인식,
         * 나머지 = @ModelAttribute 이 생략된걸로 인식 단, argument resolver 로 지정해둔 타입은 제외
         * argument resolver 는 @RequestBody와 같은 것들을 이야기함.
         */
    }


    /**
     * HttpEntity를 사용하는 방법도 있다.
    * */
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyStringV4(HttpEntity<HelloData> data) {
        HelloData helloData = data.getBody();
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }

    /*
    * json으로 응답을 보낼수도 있다.
    * */
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyStringV4(@RequestBody HelloData data) {
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return data;
    }
}