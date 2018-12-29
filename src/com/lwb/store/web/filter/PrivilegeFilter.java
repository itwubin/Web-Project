package com.lwb.store.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.lwb.store.domain.User;


public class PrivilegeFilter implements Filter {

   
    public PrivilegeFilter() {
    	
    }

	
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//判断session中是否存在用户
		HttpServletRequest myReq = (HttpServletRequest) request;
		User user = (User) myReq.getSession().getAttribute("loginUser");
		//存在则放行
		if(null!=user){
			chain.doFilter(request, response);
		}else{
			//不存在,则设置提示信息
			myReq.setAttribute("meg", "请登录后再访问吧");
			//转发只提示信息界面
			myReq.getRequestDispatcher("/jsp/info.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
