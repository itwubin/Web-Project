package com.lwb.store.dao;

import java.sql.SQLException;

import com.lwb.store.domain.User;

public interface UserDao {
	/**
	 * 用户注册
	 * @param user
	 * @throws SQLException
	 */
	void userRegist(User user) throws SQLException;
	
	/**
	 * 激活方法
	 * @param code激活码
	 * @return
	 * @throws SQLException 
	 */
	User userActive(String code) throws SQLException;
	
	/**
	 * 更新激活状态
	 * @param user
	 * @throws SQLException 
	 */
	void update(User user) throws SQLException;

	//用户登录
	User userLogin(User user) throws SQLException;

	//校验用户名是否已存在
	User checkUser(String username) throws SQLException;

	

}
