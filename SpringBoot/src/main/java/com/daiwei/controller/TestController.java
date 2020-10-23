package com.daiwei.controller;

import com.daiwei.bean.Person;
import java.util.Arrays;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/hello1")
    public String sayHello(Model model) {
        model.addAttribute("msg","nihaoa");
        return "hello";
    }

    @RequestMapping("thymeleaf")
    public String thymeleaf(ModelMap map, HttpSession session){
        session.setAttribute("name","zhangsan");
        map.put("thText","th:text设置文本内容 <b>加粗</b>");
        map.put("thUText","th:utext 设置文本内容 <b>加粗</b>");
        map.put("thValue","thValue 设置当前元素的value值");
        map.put("thEach", Arrays.asList("th:each", "遍历列表"));
        map.put("thIf","msg is not null");
        map.put("thObject",new Person("zhangsan",12,"男"));
        return "thymeleaf";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }
}
