package com.daiwei.dao;

import com.daiwei.bean.Emp;
import java.util.Map;
import org.apache.ibatis.annotations.Insert;

public interface EmpDao {

    public void save(Emp emp);
    public void update(Map<String, Object> map);
    public void delete(Emp emp);
    public Emp selectEmpByEmpNo(Integer id);

}
