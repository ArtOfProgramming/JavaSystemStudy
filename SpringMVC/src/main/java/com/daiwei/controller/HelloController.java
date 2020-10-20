package com.daiwei.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
        throws Exception {
        ModelAndView mv = new ModelAndView();
        // 添加视图名称，要跳转页面的名称
        mv.setViewName("hello");
        // 向前端页面添加属性值
        mv.addObject("hello", "hello,daiwei");
        return mv;
    }
}
