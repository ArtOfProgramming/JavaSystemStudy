package com.daiwei.servlet;

import com.daiwei.request.MyRequest;
import com.daiwei.response.MyResponse;
import java.io.IOException;

public class MyServlet extends MyHttpServlet {

    @Override
    public void doGet(MyRequest request, MyResponse response) throws IOException {
        response.write("mytomcat");
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) throws IOException {
        response.write("post mytomcat");
    }
}
