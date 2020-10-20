package com.daiwei.viewresolver;

import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.View;

public class MyVier implements View {

    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        System.out.println("model:" + model);

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.write("<h1>hello</h1>");

    }
}
