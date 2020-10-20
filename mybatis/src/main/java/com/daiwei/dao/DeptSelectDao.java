package com.daiwei.dao;

import com.daiwei.bean.Dept;
import com.daiwei.bean.Emp;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;

public interface DeptSelectDao {

    @MapKey("deptNo")
    Map<String, Dept> selectDeptAll();

    @MapKey("empNo")
    Emp selectEmpByDeptNo(int deptNo);
}
