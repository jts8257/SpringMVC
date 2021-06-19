package com.example.mvc_corepro.servlet.web.frontcontroller.v3.controller;

import com.example.mvc_corepro.servlet.domain.member.Member;
import com.example.mvc_corepro.servlet.domain.member.MemberRepository;
import com.example.mvc_corepro.servlet.web.frontcontroller.ModelView;
import com.example.mvc_corepro.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMpa) {
        List<Member> memberList = memberRepository.findAll();

        ModelView mv = new ModelView("members");
        mv.getModel().put("memberList", memberList);
        return mv;
    }
}
