package com.daiwei.bean;

import java.io.Serializable;
import java.util.List;

public class Dept implements Serializable {

    private int id;
    private int deptNo;
    private String dName;
    private String ioc;
    private List<Emp> emps;

    public Dept() {
    }

    public Dept(int id, int deptNo, String dName, String ioc) {
        this.id = id;
        this.deptNo = deptNo;
        this.dName = dName;
        this.ioc = ioc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getIoc() {
        return ioc;
    }

    public void setIoc(String ioc) {
        this.ioc = ioc;
    }

    public List<Emp> getEmps() {
        return emps;
    }

    public void setEmps(List<Emp> emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Dept1{" +
            "id=" + id +
            ", deptNo=" + deptNo +
            ", dName='" + dName + '\'' +
            ", ioc='" + ioc + '\'' +
            '}';
    }
}
