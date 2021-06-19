package com.example.mvc_corepro.servlet.web.frontcontroller.v1.controller;

import com.example.mvc_corepro.servlet.domain.member.Member;
import com.example.mvc_corepro.servlet.domain.member.MemberRepository;
import com.example.mvc_corepro.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV1 implements ControllerV1 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws SerialException, IOException, ServletException {
        String viewPath = "/WEB-INF/views/members.jsp";

        List<Member> memberList = memberRepository.findAll();

        request.setAttribute("memberList", memberList);

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
