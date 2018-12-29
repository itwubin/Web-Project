package com.lwb.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lwb.store.domain.PageModel;
import com.lwb.store.domain.Product;
import com.lwb.store.service.ProductService;
import com.lwb.store.service.serviceImp.ProductServiceImp;
import com.lwb.store.web.base.BaseServlet;


public class ProductServlet extends BaseServlet {
	
	
	public String findProductByPid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取商品pid
		String pid = req.getParameter("pid");
		//根据商品pid查询商品信息
		ProductService productService = new ProductServiceImp();
		Product product = productService.findProductByPid(pid);
		//System.out.println(product);
		//将获取到的商品放入request
		req.setAttribute("product", product);
		//转发到/jsp/product_info.jsp
		return "jsp/product_info.jsp";
	}
	
	//findProductsByCidWithPage
	public String findProductsByCidWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		  //获取cid,num
			String cid = req.getParameter("cid");
			int curNum = Integer.parseInt(req.getParameter("num"));
		  //调用业务层功能:以分页形式查询当前类别下商品信息
		  //返回PageModel对象(1_当前页商品信息2_分页3_url
			ProductService productService = new ProductServiceImp();
			PageModel pm = productService.findProductsByCidWithPage(cid,curNum);	
		  //将PageModel对象放入request
			req.setAttribute("page", pm);
		  //转发到/jsp/product_list.jsp
		  return "/jsp/product_list.jsp";
	}
}
