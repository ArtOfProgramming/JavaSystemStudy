package com.daiwei.dao;

import com.daiwei.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    @Autowired
    @Qualifier("jdbc")
    private JdbcTemplate jdbcTemplate;

    public void addUser(User user) {
        try {
            jdbcTemplate.update("insert into user (`name`, `age`, `balance`) values (?, ?, ?)",
                user.getName(), user.getAge(), user.getBalance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User queryUser(int id) {
        User user = null;
        try {
            user = jdbcTemplate
                .queryForObject("select * from user where id = ?", new BeanPropertyRowMapper<User>(User.class), id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
