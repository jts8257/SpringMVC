package com.example.mvc_corepro.servlet.web.springmvc.v1;

import com.example.mvc_corepro.servlet.domain.member.Member;
import com.example.mvc_corepro.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SpringMemberListControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process() {

        List<Member> memberList = memberRepository.findAll();

        ModelAndView mv= new ModelAndView("members");
        mv.getModel().put("memberList", memberList); // List<String> 을 넣으러면 이렇게...
        return mv;
    }
}
