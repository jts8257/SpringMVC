package com.example.newmvcproject.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {

    // Logger라는 인터페이스나, LoggerFactory는 모두 slf4j 것을 써야한다.
    // 클래스는 현재 위치의 클래스를 넣어준다.
    // getClass() or LogTestController.class
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    // @Controller였으면 String 반환시 뷰의 논리이름이 반환되는데
    // @RestController면 그냥 String이 반환된다. RestAPI 만들때 핵심적인 기능.
    public String logTest(){
        String name = "Spring";

        // 로그를 할땐 이렇게 찍어야한다.
        // 어디서 확인하냐면! 콘솔에 출력되지만 일반적으로 스프링을 띄웠을때 뜨는 문자들과 동일하게 뜬다.
        // 각각  info, warn, error 로 나온다.
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        // 로근는 어느 레벨까지 나오게할지 설정할 수 있는데, info, warn, error 은 디폴트로 출력되는 레벨이고
        // application.properties에서 이렇게 입력하면->->logging.level.com.example.newmvcproject=trace
        // 모든 레벨의 로그가 나온다.

        // 로그이 진가는 log.trace이다.
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);

        // 로그레벨 : TRACE > DEBUG > INFO > WARN > ERROR 이다.
        // 레벨(심각성)이 제일 큰 3개 (info ~ error) 가 디폴트로 출력되는 것이다.
        // 개발서버는 레벨을 debug 로, 운영서버는 info 레벨로 설정한다.
        return "ok";
    }
}
