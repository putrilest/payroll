package com.yogistore.payroll.service;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.yogistore.payroll.entity.User;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

}