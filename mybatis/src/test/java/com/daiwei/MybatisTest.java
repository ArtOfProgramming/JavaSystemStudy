package com.daiwei;

import com.daiwei.bean.Dept;
import com.daiwei.bean.Emp;
import com.daiwei.dao.DeptDao;
import com.daiwei.dao.DeptSelectDao;
import com.daiwei.dao.EmpDao;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MybatisTest {

    private static SqlSession sqlSession;
    private static SqlSession sqlSession2;
    private static SqlSession sqlSession3;
    private static SqlSession sqlSession4;

    @BeforeClass
    public static void init() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession(true);
        sqlSession2 = sqlSessionFactory.openSession(true);
        sqlSession3 = sqlSessionFactory.openSession(true);
        sqlSession4 = sqlSessionFactory.openSession(true);
    }

    @AfterClass
    public static void finish() {
        if (sqlSession != null) {
            sqlSession.close();
        }
        if (sqlSession2 != null) {
            sqlSession2.close();
        }
        if (sqlSession3 != null) {
            sqlSession3.close();
        }
        if (sqlSession4 != null) {
            sqlSession4.close();
        }
    }

    @Test
    public void TestEmpDaoInsert() throws IOException {
        if (sqlSession == null) {
            throw new NullPointerException();
        }
        EmpDao empDao = sqlSession.getMapper(EmpDao.class);
        empDao.save(new Emp(123, "hello", "nihao", 123, new Date(), 123.1, 32432.1, 1));
        empDao.save(new Emp(123, "hello1", "nihao2", 123, new Date(), 123.1, 32432.1, 1));
    }

    @Test
    public void TestEmpDaoUpdate() throws IOException {
        if (sqlSession == null) {
            throw new NullPointerException();
        }
        EmpDao empDao = sqlSession.getMapper(EmpDao.class);
        Map<String, Object> map = new HashMap<>();

        map.put("empNo", 100);
        map.put("id", 3);
        map.put("eName", "hai");
        map.put("job", "programmer");

        empDao.update(map);
    }

    @Test
    public void TestEmpDaoQuery() throws IOException {
        if (sqlSession == null) {
            throw new NullPointerException();
        }
        EmpDao empDao = sqlSession.getMapper(EmpDao.class);
        Emp emp = empDao.selectEmpByEmpNo(5);
        System.out.println(emp);
    }

    @Test
    public void TestEmpDaoDelete() throws IOException {
        if (sqlSession == null) {
            throw new NullPointerException();
        }
        EmpDao empDao = sqlSession.getMapper(EmpDao.class);
        Emp emp = new Emp();
        emp.setId(1);
        empDao.delete(emp);
    }

    @Test
    public void TestDeptSelectAll() {
        if (sqlSession == null) {
            throw new NullPointerException();
        }
        DeptDao deptDao = sqlSession.getMapper(DeptDao.class);
        Map<String, Dept> map = deptDao.selectAll();
        sqlSession.commit();
        Map<String, Dept> map2 = deptDao.selectAll();
        System.out.println(map);
        System.out.println(map2);
    }

    @Test
    public void TestDeptSelectAllSecondCache() {
        if (sqlSession == null) {
            throw new NullPointerException();
        }
        DeptDao deptDao1 = sqlSession.getMapper(DeptDao.class);
        DeptDao deptDao2 = sqlSession2.getMapper(DeptDao.class);
        DeptDao deptDao3 = sqlSession3.getMapper(DeptDao.class);
        DeptDao deptDao4 = sqlSession4.getMapper(DeptDao.class);
        Map<String, Dept> map = deptDao1.selectAll();
        sqlSession.commit();
        Map<String, Dept> map2 = deptDao2.selectAll();
        sqlSession2.commit();
        Map<String, Dept> map3 = deptDao3.selectAll();
        sqlSession3.commit();
        Map<String, Dept> map4 = deptDao4.selectAll();
        sqlSession4.commit();
        System.out.println(map);
        System.out.println(map2);
        System.out.println(map3);
        System.out.println(map4);
    }


    @Test
    public void TestDeptStepSelectAll() {
        if (sqlSession == null) {
            throw new NullPointerException();
        }
        DeptSelectDao deptSelectDao = sqlSession.getMapper(DeptSelectDao.class);
        Map<String, Dept> map = deptSelectDao.selectDeptAll();
        System.out.println("lazy");
    }
}
