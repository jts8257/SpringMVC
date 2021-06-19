package com.example.mvc_corepro.servlet.web.frontcontroller.v3;

import com.example.mvc_corepro.servlet.web.frontcontroller.ModelView;
import com.example.mvc_corepro.servlet.web.frontcontroller.MyView;
import com.example.mvc_corepro.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.mvc_corepro.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.mvc_corepro.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// FrontController를 제외한 다른 Controller 들이 HttpServletRequest 나 Response등을 가지고 있을 필요가 없다.
// V2까지 위의 요소들을 가지고 있었던 이유는 오로지 model로서 request를 이용했기 때문인데, model객체를 따로 만들어서 쓴다면 해결될 문제다.

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*" )
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("frontControllerServletV3.service");
        String requestURI = req.getRequestURI();

        System.out.println("requestURI :"  + requestURI);
        ControllerV3 controller = controllerMap.get(requestURI);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            System.out.println("'" + requestURI + "' is NOT FOUND(404)");
            return ;
        }

        //paramMap
        Map<String, String> paramMap = createParamMap(req);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName(); // 논리이름만 나옴, viewResolver가 있어야함
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), req, resp);
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
