package com.daiwei.druid;

import com.daiwei.druid.bean.Person;
import com.daiwei.druid.util.JdbcUtils;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class DruidTest {

    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());
//        int count = jdbcTemplate.update("insert into user (name, age, balance) values (?, ?, ?)", "hello", 16, 3000);
//        System.out.println(count);
//        List<Person> list = jdbcTemplate.query("select * from user", new RowMapper<Person>() {
//
//            @Override
//            public Person mapRow(ResultSet resultSet, int i) throws SQLException {
//                Person person = new Person();
//                person.setId(resultSet.getInt("id"));
//                person.setAge(resultSet.getInt("age"));
//                person.setName(resultSet.getString("name"));
//                person.setBalance(resultSet.getInt("balance"));
//                return person;
//            }
//        });
        List<Person> list = jdbcTemplate.query("select * from user ", new BeanPropertyRowMapper<Person>(Person.class));
        for (Person person : list) {
            System.out.println(person.toString());
        }
        while (true) {
            for (Person person : list) {
                System.out.println(person.toString());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
