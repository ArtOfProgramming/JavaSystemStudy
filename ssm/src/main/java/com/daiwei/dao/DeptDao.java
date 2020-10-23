package com.daiwei.dao;

import com.daiwei.bean.Dept;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface DeptDao {

    @MapKey("deptNo")
    Map<String, Dept> selectAll();
}
