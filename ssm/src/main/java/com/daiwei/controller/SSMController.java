package com.daiwei.controller;

import com.daiwei.bean.Dept;
import com.daiwei.bean.Emp;
import com.daiwei.bean.User;
import com.daiwei.dao.DeptSelectDao;
import com.daiwei.dao.EmpDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SSMController {

    final private DeptSelectDao deptSelectDao;
    final private EmpDao empDao;

    @Autowired
    public SSMController(DeptSelectDao deptSelectDao, EmpDao empDao) {
        this.deptSelectDao = deptSelectDao;
        this.empDao = empDao;
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("dept")
    @ResponseBody
    public Map<Object, Dept> getDept() {
        Map<Object, Dept> map = deptSelectDao.selectDeptAll();
        List<User> list = jdbcTemplate.query("select * from user ", new BeanPropertyRowMapper<User>(User.class));
        System.out.println(list);
        System.out.println(map);
        return deptSelectDao.selectDeptAll();
    }
}
