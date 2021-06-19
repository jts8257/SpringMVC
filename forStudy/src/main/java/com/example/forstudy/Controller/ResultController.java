package com.example.forstudy.Controller;


import com.example.forstudy.HttpReqInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ResultController<printStartLine> {

    private HttpReqInfo httpReqInfo = new HttpReqInfo();

    @PostConstruct
    private void start(){
        System.out.println("controller constructed");
    }

    @GetMapping("result")
    public String getResp(HttpServletRequest req, Model model) {
        httpReqInfo.printStartLine(req);
            //httpReqInfo.printHeaders(req);
            //httpReqInfo.printHeaderUtils(req);
            //httpReqInfo.printEtc(req);
            int n = Integer.parseInt(req.getParameter("num"));
            if (n < 1 || n > 9) {
                model.addAttribute("status", "0");
                model.addAttribute("msg", "Not a Valid Number (1 <= n <= 9)");
            } else {
                model.addAttribute("status", "1");
                model.addAttribute("msg", "A Valid Number (" + n +")");
                model.addAttribute("num", n);
                Map<String, String> numMap = SeqBuilder(n);
                model.addAttribute("numMap", numMap);
        }
        return "result";
    }

    @PostMapping("result")
    public String postResp(HttpServletRequest req, Model model) {
        httpReqInfo.printStartLine(req);
        //httpReqInfo.printHeaders(req);
        //httpReqInfo.printHeaderUtils(req);
        //httpReqInfo.printEtc(req);
        int n = Integer.parseInt(req.getParameter("num"));
        if (n < 1 || n > 9) {
            model.addAttribute("status", "0");
            model.addAttribute("msg", "Not a Valid Number (1 <= n <= 9)");
        } else {
            model.addAttribute("status", "1");
            model.addAttribute("msg", "A Valid Number (" + n +")");
            model.addAttribute("num", n);
            Map<String, String> numMap = SeqBuilder(n);
            model.addAttribute("numMap", numMap);
        }
        return "result";
    }

    @GetMapping("result2")
    public String getResp2(HttpServletRequest req, Model model) {
        httpReqInfo.printStartLine(req);
        //httpReqInfo.printHeaders(req);
        //httpReqInfo.printHeaderUtils(req);
        //httpReqInfo.printEtc(req);
        int n = Integer.parseInt(req.getParameter("num"));
        if (n < 1 || n > 9) {
            model.addAttribute("status", "0");
            model.addAttribute("msg", "Not a Valid Number (1 <= n <= 9)");
        } else {
            model.addAttribute("status", "1");
            model.addAttribute("msg", "A Valid Number (" + n +")");
            model.addAttribute("num", n);
            Map<String, String> numMap = SeqBuilder(n);
            model.addAttribute("numMap", numMap);
        }
        return "result2";
    }

    private Map<String, String> SeqBuilder(int n) {
        Map<String, String> numMap = new HashMap<>();
        for(int i = 1; i < 10; i++) {
            numMap.put("key"+String.valueOf(i), String.valueOf(i * n));
        }
        return numMap;
    }
}


















