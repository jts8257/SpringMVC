package com.example.mvc_corepro.servlet.web.frontcontroller.v5;

import com.example.mvc_corepro.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

public interface MyHandlerAdapter {

    boolean supports(Object handler);

    ModelView handle(HttpServletRequest req,
                     HttpServletResponse resp,
                     Object handler) throws ServletException, IOException;

}
