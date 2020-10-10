package com.daiwei.jdbc;

import com.daiwei.jdbc.bean.User;
import com.daiwei.jdbc.util.JdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcTest {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            // 1.导入驱动jar包
            // 2.注册驱动
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            // 3.获取数据库连接对象
//            connection = DriverManager.getConnection(
//                "jdbc:mysql://127.0.0.1:3306/memb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai",
//                "root", "parking");
            connection = JdbcUtils.getConnection();
            // 4.定义sql语句
            String sql = "select * from user";
            // 5.获取执行sql的statement对象
            statement = connection.createStatement();
            int count = statement.executeUpdate("insert into user (age, name, balance) values (14, 'hai', 20)");
            count = statement.executeUpdate("update  user set balance = 300 where name='hai'");
            count = statement.executeUpdate("delete from user  where name='hai'");
            // 6.执行Sql
            resultSet = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setAge(resultSet.getInt("age"));
                user.setName(resultSet.getString("name"));
                user.setBalance(resultSet.getInt("balance"));
                users.add(user);
            }
            for (User user : users) {
                System.out.println(user.toString());
            }
        } catch (Exception e) {

        } finally {
            JdbcUtils.close(resultSet, statement, connection);
//            try {
//                connection.close();
//                statement.close();
//            } catch (Exception e) {
//
//            }
        }

    }
}
