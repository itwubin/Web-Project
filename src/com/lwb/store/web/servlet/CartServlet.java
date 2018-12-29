package com.lwb.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lwb.store.domain.Cart;
import com.lwb.store.domain.CartItem;
import com.lwb.store.domain.Product;
import com.lwb.store.service.ProductService;
import com.lwb.store.service.serviceImp.ProductServiceImp;
import com.lwb.store.web.base.BaseServlet;


public class CartServlet extends BaseServlet {
	
	public String addCartItemToCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		   //从session获取购物车
			Cart cart = (Cart) req.getSession().getAttribute("cart");
		   //如果获取不到,创建购物车对象,放在session中
			if(null==cart){
				//创建购物车对象,放在session中  ,刚开始,你放错了
				cart = new Cart();
				//req.setAttribute("cart", cart);//放错了,不能用request,用session
				req.getSession().setAttribute("cart", cart);
			}
		   //如果获取到,使用即可
		   //获取到商品id,数量        
			String pid = req.getParameter("pid");
			int num = Integer.parseInt(req.getParameter("quantity")) ;
	       //通过商品id查询都商品对象
			ProductService productService = new ProductServiceImp();
			Product product = productService.findProductByPid(pid);
	       //获取到待购买的购物项  
			CartItem cartItem = new CartItem();
			cartItem.setNum(num);
			cartItem.setProduct(product);
	       //调用购物车上的方法
			cart.addCartItemToCar(cartItem);
	       //重定向到/jsp/cart.jsp  
			resp.sendRedirect("/store_v1/jsp/cart.jsp");
			//试试请求转发,看看会有啥问题?????
		return null;
		
	}
	
	//removeCartItem
	public String removeCartItem(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		 //获取待删除商品pid
		String pid = req.getParameter("pid");
         //获取到购物车
		Cart cart = (Cart) req.getSession().getAttribute("cart");
         //调用购物车删除购物项方法
		cart.removeCartItem(pid);
         //重定向到/jsp/cart.jsp
		resp.sendRedirect("/store_v1/jsp/cart.jsp");
		return null;
	}
	
	//clearCart
	public String clearCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取购物车
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		//调用购物车的清空方法
		cart.clearCart();
		//重定向到/jsp/cart.jsp页面
		resp.sendRedirect("/store_v1/jsp/cart.jsp");
		return null;
	}

}
