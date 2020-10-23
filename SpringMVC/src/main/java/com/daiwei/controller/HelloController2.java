package com.daiwei.controller;


import com.daiwei.bean.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HelloController2 {

    @RequestMapping("/login")
    public String hello(Map<String,String> map) {
        return "login";
    }


    @RequestMapping("/hello")
    public String hello2() {
        return "hello";
    }

//    @RequestMapping("/hello3")
//    public String hello3() {
//        return "hello3";
//    }

    @RequestMapping("/hello4")
    @ResponseBody
    public String hello4(String converterValue) {
        System.out.println(converterValue);
        return "msb:hello";
    }

    @RequestMapping("/json")
    @ResponseBody
    public List<User> json() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "aa", "bb", 12, new Date()));
        list.add(new User(1, "bb", "bb", 13, new Date()));
        list.add(new User(1, "cc", "bb", 14, new Date()));
        list.add(new User(1, "dd", "bb", 15, new Date()));
        return list;
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request) throws Exception {

        //获取要下载的路径
        ServletContext servletContext = request.getServletContext();
        String realPath = servletContext.getRealPath("/scripts/jquery-1.9.1.min.js");
        //通过io流对文件进行读写
        FileInputStream fileInputStream = new FileInputStream(realPath);
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        fileInputStream.close();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Disposition","attachment;filename=jquery-1.9.1.min.js");
        return  new ResponseEntity<byte[]>(bytes,httpHeaders, HttpStatus.OK);
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile[] multipartFile,@RequestParam(value = "desc",required = false) String desc) throws IOException {

        System.out.println(desc);
        //获取文件的名称
        for (MultipartFile file : multipartFile) {
            if(!file.isEmpty()){
                System.out.println(file.getOriginalFilename());
                file.transferTo(new File("d:\\file\\"+file.getOriginalFilename()));
            }
        }
        return "hello";
    }

    @RequestMapping("/login1")
    public String locale() {
        return "login1";
    }

    @RequestMapping("/exception")
    public String exception() {
        int i = 10 / 0;
        return "hello";
    }
}
