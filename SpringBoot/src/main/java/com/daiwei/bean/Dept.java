package com.daiwei.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author daiwei
 * @since 2020-10-21
 */
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer deptno;

    private String dname;

    @TableField("Ioc")
    private String Ioc;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getIoc() {
        return Ioc;
    }

    public void setIoc(String Ioc) {
        this.Ioc = Ioc;
    }

    @Override
    public String toString() {
        return "Dept{" +
        "id=" + id +
        ", deptno=" + deptno +
        ", dname=" + dname +
        ", Ioc=" + Ioc +
        "}";
    }
}
