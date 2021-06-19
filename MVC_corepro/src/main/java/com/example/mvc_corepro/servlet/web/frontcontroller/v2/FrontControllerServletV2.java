package com.example.mvc_corepro.servlet.web.frontcontroller.v2;

import com.example.mvc_corepro.servlet.web.frontcontroller.MyView;
import com.example.mvc_corepro.servlet.web.frontcontroller.v1.ControllerV1;
import com.example.mvc_corepro.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import com.example.mvc_corepro.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import com.example.mvc_corepro.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 그냥 forward부분만 리팩토링 된 것임.

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*" )
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("frontControllerServletV2.service");
        String requestURI = req.getRequestURI();

        System.out.println("requestURI :"  + requestURI);
        ControllerV2 controller = controllerMap.get(requestURI);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            System.out.println("'" + requestURI + "' is NOT FOUND(404)");
            return ;
        }

        MyView view = controller.process(req, resp);
        System.out.println("controller : " + controller);

        view.render(req, resp);
    }
}
