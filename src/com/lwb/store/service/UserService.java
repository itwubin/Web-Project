package com.lwb.store.service;

import java.sql.SQLException;

import com.lwb.store.domain.User;

public interface UserService {
	//用户注册
	void userRegist(User user) throws SQLException;
	//用户激活
	boolean userActive(String code) throws SQLException;
	//用户登录
	User userLogin(User user) throws SQLException;
	//校验用户名是否存在
	User checkUser(String username) throws SQLException;

}
