package io.bsnet.breeze.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ApiController {
    @RequestMapping("/api")
    public ModelAndView basic(){
        List<String> inL = new ArrayList<String>();
        inL.add("001");
        inL.add("002");
        inL.add("003");
        inL.add("004");
        inL.add("005");

        ModelAndView mv = new ModelAndView();
        mv.setViewName("jsonView");

        mv.addObject("numberList", inL);

        return mv;
    }
}
