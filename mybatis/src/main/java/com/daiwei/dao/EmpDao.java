package com.daiwei.dao;

import com.daiwei.bean.Emp;
import java.util.Map;
import org.apache.ibatis.annotations.Insert;

public interface EmpDao {

    public void save(Emp emp);
    public Integer update(Map<String, Object> map);
    public Integer delete(Emp emp);
    public Emp selectEmpByEmpNo(Integer id);

}
