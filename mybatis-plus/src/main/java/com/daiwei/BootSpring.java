package com.daiwei;

import com.daiwei.dao.EmpDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BootSpring {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "spring.xml");
        EmpDao empDao = (EmpDao) context.getBean("empDao");
        System.out.println(empDao.selectList(null));

    }
}
