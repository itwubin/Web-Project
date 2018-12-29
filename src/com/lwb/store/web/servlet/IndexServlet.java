package com.lwb.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lwb.store.domain.Category;
import com.lwb.store.domain.Product;
import com.lwb.store.service.CategoryService;
import com.lwb.store.service.ProductService;
import com.lwb.store.service.serviceImp.CategoryServiceImp;
import com.lwb.store.service.serviceImp.ProductServiceImp;
import com.lwb.store.web.base.BaseServlet;


public class IndexServlet extends BaseServlet {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//调用业务层对象获取分类信息
		CategoryService categoryService = new CategoryServiceImp();
		List<Category> list = categoryService.getAllCats();
		//将获返回的集合放入request中
		req.setAttribute("allCats", list);
		
		//调用业务层查询最新商品,查询最热商品,返回2个集合
			//查询最新商品
		ProductService productService = new ProductServiceImp();
		List<Product> list01 = productService.findNewProduct();
			//查询最热商品
		List<Product> list02 = productService.findHotProduct();
		
		//System.out.println(list01);
		//System.out.println(list02);
		
		
		//将2个集合放入到request
		req.setAttribute("Bestnew", list01);
		req.setAttribute("Besthot", list02);
		
		//转发到真实页面
		return "/jsp/index.jsp";
	}
}
