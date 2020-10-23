package com.daiwei.controller;


import com.daiwei.bean.Dept;
import com.daiwei.config.datasources.DataSource;
import com.daiwei.config.datasources.DataSourceType;
import com.daiwei.service.DeptService;
import com.daiwei.service.EmpService;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author daiwei
 * @since 2020-10-21
 */
@RestController
public class DeptController {

    @Autowired(required = false)
    private DeptService deptService;

    @RequestMapping("/dept/{id}")
    @DataSource(DataSourceType.SLAVE)
    @Transactional(propagation = Propagation.REQUIRED)
    public Dept getById(@PathVariable("id") Integer id) {
        return deptService.getById(id);
    }
}

