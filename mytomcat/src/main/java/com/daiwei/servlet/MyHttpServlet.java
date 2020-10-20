package com.daiwei.servlet;

import com.daiwei.request.MyRequest;
import com.daiwei.response.MyResponse;

public abstract class MyHttpServlet {

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";

    public abstract void doGet(MyRequest request, MyResponse response) throws Exception;
    public abstract void doPost(MyRequest request, MyResponse response) throws Exception;

    /**
     * 根据请求方式来判断调用那种处理方法
     * @param request
     * @param response
     */
    public void service(MyRequest request, MyResponse response) throws Exception {
        if (METHOD_GET.equals(request.getRequestMethod())) {
            doGet(request, response);
        } else if(METHOD_POST.equals(request.getRequestMethod())) {
            doPost(request, response);
        }
    }
}
