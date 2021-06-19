package com.example.mvc_corepro.servlet.web.frontcontroller.v2.controller;

import com.example.mvc_corepro.servlet.domain.member.Member;
import com.example.mvc_corepro.servlet.domain.member.MemberRepository;
import com.example.mvc_corepro.servlet.web.frontcontroller.MyView;
import com.example.mvc_corepro.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {


    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> memberList = memberRepository.findAll();
        request.setAttribute("memberList", memberList);

        return new MyView("/WEB-INF/views/members.jsp");
    }
}
