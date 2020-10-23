package com.daiwei.controller;

import com.daiwei.bean.Emp;
import com.daiwei.multidatasource.MultiDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestMultiDataSourceController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/master")
    @MultiDataSource("master")
    public Emp getEmpByIdFromMaster(@RequestParam("id") Integer id) {
        return jdbcTemplate.queryForObject("select * from emp where id = ?",
            new BeanPropertyRowMapper<Emp>(Emp.class), id);
    }

    @RequestMapping("/slave")
    @MultiDataSource("slave")
    public Emp getEmpByIdFromSlave(@RequestParam("id") Integer id) {
        return jdbcTemplate.queryForObject("select * from emp where id = ?",
            new BeanPropertyRowMapper<Emp>(Emp.class), id);
    }
}
