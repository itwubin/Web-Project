package com.lwb.store.dao.daoImp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.lwb.store.dao.UserDao;
import com.lwb.store.domain.User;
import com.lwb.store.utils.JDBCUtils;

import jdk.nashorn.internal.scripts.JD;

public class UserDaoImp implements UserDao {
	//用户注册
	@Override
	public void userRegist(User user) throws SQLException {
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		//创建QueryRunner对象
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource()); 
		Object[] params = {user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),
				user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()};
		queryRunner.update(sql, params);
	}
	
	//激活用户
	@Override
	public User userActive(String code) throws SQLException {
		String sql = "select*from user where code=?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		User user = queryRunner.query(sql, new BeanHandler<User>(User.class), code);
		return user;
	}
	
	//更新激活状态
	@Override
	public void update(User user) throws SQLException {
		String sql = "update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? where uid=?";	
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params = {user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),
				user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode(),user.getUid()};
		queryRunner.update(sql, params);
	}

	
	//用户登录
	@Override
	public User userLogin(User user) throws SQLException {
		String sql = "select*from user where username=? and password=?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		return queryRunner.query(sql, new BeanHandler<User>(User.class), user.getUsername(),user.getPassword());
	}
	
	//查询用户名是否存在
	@Override
	public User checkUser(String username) throws SQLException {
		String sql = "select*from user where username=?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		User user = queryRunner.query(sql, new BeanHandler<User>(User.class), username);
		return user;
	}	
	
	

	
	
}
