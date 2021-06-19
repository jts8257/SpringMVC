package com.example.mvc_corepro.servlet.web.frontcontroller.v3.controller;

import com.example.mvc_corepro.servlet.domain.member.Member;
import com.example.mvc_corepro.servlet.domain.member.MemberRepository;
import com.example.mvc_corepro.servlet.web.frontcontroller.ModelView;
import com.example.mvc_corepro.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // paramMap의 값은 FrontController에서 주입을 해줌. 그래서 제대로 작동함.

        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv;
    }
}
