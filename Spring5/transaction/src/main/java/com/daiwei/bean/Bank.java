package com.daiwei.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Scope("prototype")
public class Bank {

    private User user;
    private boolean login = false;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean login(int id) {
        try {
            user = jdbcTemplate.queryForObject("select * from user where id = ?", new BeanPropertyRowMapper<User>(User.class), id);
        } catch (Exception e) {
            return false;
        }
        login = true;
        return true;
    }

    public int deposit (int money) {
        if (!login || money < 0) {
            return -1;
        }
        jdbcTemplate.update("update user set balance = balance + ? where id = ?", money, user.getId());
        return jdbcTemplate.queryForObject("select balance from user where id = ?", Integer.class, user.getId());
    }

    public int withdraw(int money) {
        if (!login || money < 0) {
            return -1;
        }
        jdbcTemplate.update("update user set balance = balance + ? where id = ?", money, user.getId());
        return jdbcTemplate.queryForObject("select balance from user where id = ?", Integer.class, user.getId());
    }

    @Transactional
    public boolean transfer(int toID, int money) {
        if (!login || money < 0) {
            return false;
        }

        int balance = jdbcTemplate.queryForObject("select balance from user where id = ?", Integer.class, user.getId());
        if (balance - money < 0) {
            System.out.println("余额不足");
            return false;
        }
        jdbcTemplate.update("update user set balance = balance - ? where id = ?", money, user.getId());
        int i = 0 / 0;
        jdbcTemplate.update("update user set balance = balance + ? where id = ?", money, toID);
        return true;
    }

    public int getBalance() {
        if (!login) {
            return -1;
        }
        return jdbcTemplate.queryForObject("select balance from user where id = ?", Integer.class, user.getId());
    }
}
