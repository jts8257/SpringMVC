package com.example.mvc_corepro.servlet.web.frontcontroller.v3;

import com.example.mvc_corepro.servlet.web.frontcontroller.ModelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMpa);
}
