package com.lwb.store.service.serviceImp;

import java.sql.SQLException;

import com.lwb.store.dao.UserDao;
import com.lwb.store.dao.daoImp.UserDaoImp;
import com.lwb.store.domain.User;
import com.lwb.store.service.UserService;

public class UserServiceImp implements UserService {

	//用户注册
	@Override
	public void userRegist(User user) throws SQLException {
		//调用DAO层对象
		UserDao ud = new UserDaoImp();
		ud.userRegist(user);
	}

	//用户激活
	@Override
	public boolean userActive(String code) throws SQLException {
		//调用DAO层对象
		UserDao ud = new UserDaoImp();
		User user = ud.userActive(code);
		
		if(user !=null){
			//存在用户信息
			//设置激活状态,激活码清除
			user.setState(1);
			user.setCode(null);
			ud.update(user);
			return true;
		}else {
			return false;
		}
		
	}


	//用户登录验证
	@Override
	public User userLogin(User user) throws SQLException {
		//调用DAO层对象
			UserDao ud = new UserDaoImp();
			User uu = ud.userLogin(user);
			if(null ==uu){
				throw new RuntimeException("用户密码错误!");
			}else if(uu.getState()==0){
				throw new RuntimeException("用户未激活!");
			}else{
				return uu;
			}
	}

	//校验用户名是否存在
	@Override
	public User checkUser(String username) throws SQLException {
		//调用DAO层对象
		UserDao ud = new UserDaoImp();
		User user = ud.checkUser(username);
		return user;
	}
	

	

	

}
