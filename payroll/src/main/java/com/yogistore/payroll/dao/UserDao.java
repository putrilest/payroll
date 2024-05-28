package com.yogistore.payroll.dao;

import com.yogistore.payroll.entity.User;

public interface UserDao {
    User findByUserName(String userName);
}

