package com.example.mvc_corepro.servlet.web.frontcontroller.v1;

import com.example.mvc_corepro.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.example.mvc_corepro.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.example.mvc_corepro.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*" )
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("frontControllerServletV1.service");
        String requestURI = req.getRequestURI();

        System.out.println("requestURI :"  + requestURI);
        ControllerV1 controller = controllerMap.get(requestURI);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            System.out.println("'" + requestURI + "' is NOT FOUND(404)");
            return ;
        }

        try {
            controller.process(req, resp);
        } catch (SerialException throwables) {
            throwables.printStackTrace();
        }
    }
}
