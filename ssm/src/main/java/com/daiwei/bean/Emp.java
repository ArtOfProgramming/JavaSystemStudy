package com.daiwei.bean;

import java.io.Serializable;
import java.util.Date;

public class Emp implements Serializable {

    private int id;
    private int empNo;
    private String eName;
    private String job;
    private int mgr;
    private Date hireDate;
    private double sal;
    private double comm;
    private int deptNo;

    public Emp() {
    }

    public Emp(int empNo, String eName, String job, int mgr, Date hireDate, double sal, double comm,
        int deptNo) {
        this.empNo = empNo;
        this.eName = eName;
        this.job = job;
        this.mgr = mgr;
        this.hireDate = hireDate;
        this.sal = sal;
        this.comm = comm;
        this.deptNo = deptNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMgr() {
        return mgr;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public double getComm() {
        return comm;
    }

    public void setComm(double comm) {
        this.comm = comm;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    @Override
    public String toString() {
        return "Emp{" +
            "id=" + id +
            ", empNo=" + empNo +
            ", eName='" + eName + '\'' +
            ", job='" + job + '\'' +
            ", mgr=" + mgr +
            ", hireDate=" + hireDate +
            ", sal=" + sal +
            ", comm=" + comm +
            ", deptNo=" + deptNo +
            '}';
    }
}
