package com.example.newmvcproject.requestmapping;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /* 경로변수는 url에 쿼리 파라미터(...?userId=userA)로 보내는게 아니라, 그 자체가 값을 갖고 있는 경우다.
    * 이렇게 정말 많이 사용한다.
    * Http API들이 선호한다.
    * */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        // 파라미터를 '@PathVariable String userId' 로 축약할 수 있음
        log.info("mappingPath userId={}", data);
        return "OK";
    }

    /*다중 경로변수 */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long
            orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }


    /* 잘 사용하지는 않는다
    * url경로뿐만 아니라 파라미터 조건까지 매칭시킨 경우
    * url만 일치하고 파라미터가 없으면 호출되지 않음
    * 파라미터는 다음과 같이 매핑할 수 있음.
    * params="mode",
    * params="!mode"
    * params="mode=debug"
    * params="mode!=debug" (! = )
    * params = {"mode=debug","data=good"}
    * 아래 조건은 {url}?mode=debug 가 되어야함.
     * */

    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     * postman을 써서 headers에 key = mode ,value = debug 로 해야함
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    // consumes에 "application/json" 직접 문자로 넣기 보다는
    // MediaType.APPLICATION_JSON_VALUE 처럼 하는게 더 좋을 수도 있다.(org.springframwork.http)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * 내가 생산해내는 response 를 매칭시키는 방법도 있음.
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
