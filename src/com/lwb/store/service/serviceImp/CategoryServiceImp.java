package com.lwb.store.service.serviceImp;

import java.sql.SQLException;
import java.util.List;

import com.lwb.store.dao.CategoryDao;
import com.lwb.store.dao.daoImp.CategoryDaoImp;
import com.lwb.store.domain.Category;
import com.lwb.store.service.CategoryService;
import com.lwb.store.utils.JedisUtils;

import redis.clients.jedis.Jedis;

public class CategoryServiceImp implements CategoryService {

	CategoryDao cd = new CategoryDaoImp();
	@Override
	public List<Category> getAllCats() throws SQLException {

		return cd.getAllCats();
	}

	@Override
	public void addCategory(Category c) throws SQLException {
		//本质就是向数据库插入一条数据
		cd.addCategory(c);
		//更新redis缓存
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
	}

}
