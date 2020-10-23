package com.daiwei;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daiwei.bean.Emp;
import com.daiwei.dao.EmpDao;
import java.util.Date;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml", "classpath:mybatis-plus-config.xml"})
public class MybatisPlusTest {

    @Autowired
    private EmpDao empDao;

    @Test
    public void selectEmpTest() {
        System.out.println(empDao.selectList(null));
    }

    @Test
    public void insertEmpTest() {
        Emp emp = new Emp();
        emp.setEmpNo(123);
        emp.setDeptNo(105);
        emp.seteName("haiii");
        emp.setHireDate(new Date());
        empDao.insert(emp);
        System.out.println(emp.getId());
    }

    @Test
    public void selectPageTest() {
        Page<Emp> page = empDao.selectPage(new Page<Emp>(2, 2), new QueryWrapper<Emp>().between("id", 100, 200));
        System.out.println(page.getRecords());
    }
}
