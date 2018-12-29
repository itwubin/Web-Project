package com.lwb.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lwb.store.domain.Category;
import com.lwb.store.service.CategoryService;
import com.lwb.store.service.serviceImp.CategoryServiceImp;
import com.lwb.store.utils.JedisUtils;
import com.lwb.store.web.base.BaseServlet;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;


public class CategoryServlet extends BaseServlet {
	
	public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//在redis中获取缓存数据
		String jsonStr="";
		Jedis jedis = JedisUtils.getJedis();
		jsonStr = jedis.get("allCats");  //商品类的所有分类信息allCats
		if(null==jsonStr || "".equals(jsonStr)){
			System.out.println("redis缓存没有数据");
			//调用业务层获取全部分类
			CategoryService categoryService = new CategoryServiceImp();
			List<Category> list = categoryService.getAllCats();
			//将获取的分类信息转为json格式
			jsonStr = JSONArray.fromObject(list).toString();
			System.out.println(jsonStr);
			jedis.set("allCats",jsonStr);
			
			//告诉浏览器,本次响应的内容格式是json
			resp.setContentType("application/json;charset=utf-8");
			resp.getWriter().write(jsonStr);  //响应	
		}else {
			System.out.println("redis缓存有数据");
			resp.setContentType("application/json;charset=utf-8");
			resp.getWriter().write(jsonStr);
		}
		JedisUtils.closeJedis(jedis);
		return null;	
	}
}
