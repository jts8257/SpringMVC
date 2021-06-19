package com.example.mvc_corepro.servlet.web.frontcontroller.v3.controller;

import com.example.mvc_corepro.servlet.web.frontcontroller.ModelView;
import com.example.mvc_corepro.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMpa) {
        return new ModelView("new-form"); // path전체가 아니라 논리적인 이름을 넘김. 어떤게 논리적인 이름인지는 개발자가 정함
    }
}
