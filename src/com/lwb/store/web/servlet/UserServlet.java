package com.lwb.store.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Synthesizer;

import com.lwb.store.domain.User;
import com.lwb.store.service.UserService;
import com.lwb.store.service.serviceImp.UserServiceImp;
import com.lwb.store.utils.MailUtils;
import com.lwb.store.utils.MyBeanUtils;
import com.lwb.store.utils.UUIDUtils;
import com.lwb.store.web.base.BaseServlet;

public class UserServlet extends BaseServlet{
	
	//注册界面
	public String registUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		return "/jsp/register.jsp";
	}
	
	//登录界面loginUI
	public String loginUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		return "/jsp/login.jsp";
	}
	
	//用户注册
	public String userRegist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Map<String, String[]> map = req.getParameterMap();
		User user = new User();
		MyBeanUtils.populate(user, map);
		//System.out.println("获取注册时的信息请求:"+user);
		//用户其他属性赋值
		user.setUid(UUIDUtils.getId());
		user.setState(0);//默认状态为0
		user.setCode(UUIDUtils.getUUID64());
		//调用业务层对象
		UserService userService = new UserServiceImp();
		try {
			userService.userRegist(user);
			//成功注册,发送邮件激活,提示信息
			//发送邮件
			MailUtils.sendMail(user.getEmail(), user.getCode());
			req.setAttribute("meg", "恭喜您注册成功,请激活");
			
		} catch (Exception e) {
			//失败提示
			req.setAttribute("meg", "注册失败,请重新注册");
			e.printStackTrace();
		}
		return "/jsp/info.jsp";
	}

	//用户激活
	public String active(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException{
		//http://localhost:8080/store_v1/UserServlet?method=active&code=8F043CA4313E4C4EA244782D27B0BA09805F9144A14746A09A252FA460AE0EC6
		//获取激活码
		//服务端获取到激活码,和数据库中已经存在的激活码对比,如果存在,激活成功,更改用户激活状态1,转发到登录页面,提示:激活成功,请登录.
		String code = req.getParameter("code");
		//调用业务层对象
		UserService userService = new UserServiceImp();
		boolean flag = userService.userActive(code);
		if(flag==true){
			//成功激活,向request设置提示信息
			req.setAttribute("meg", "用户激活成功,请登录");
			return "/jsp/login.jsp";
		}else {
			//激活失败,设置提示信息
			req.setAttribute("meg", "用户激活失败,请重新激活!");
			return "/jsp/info.jsp";
		}
	}
	
	//登录验证loginCheck
	public String userLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException{
		//获取请求信息
		/*String username = req.getParameter("username");
		String password = req.getParameter("password");*/
		//System.out.println("获取的登录时的信息请求:"+username+":"+password);
		User user = new User();
		MyBeanUtils.populate(user, req.getParameterMap());  //这里很明显只放着用户名和密码
		//处理请求信息
			//调用业务层对象
		UserService userService = new UserServiceImp();
		User user02 = null;  //从数据库查询返回的user信息更全面  -->这一步很重要,涨知识了吧
		try {
			user02 = userService.userLogin(user);
			//用户登录成功,将用户信息放入session中
			req.getSession().setAttribute("loginUser", user02);
			//重定向至主页面
			resp.sendRedirect("/store_v1/index.jsp");
			return null;   //这一步很重要-->查看BaseServlet中,如果不等于null,将请求转发,既然已经重定向,不可再请求转发
		} catch (Exception e) {
			//业务层自定义异常
			String msg = e.getMessage();
			System.out.println(msg);
			//设置提示用户登录失败的信息
			req.setAttribute("meg", msg);
			//重定向至登录页面
			return "/jsp/login.jsp";
		}
		
	}
	
	//退出功能
	public String logOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//清除session
		req.getSession().invalidate();
		//重定向到首页
		resp.sendRedirect("/store_v1/index.jsp");
		return null;
	}
		
	//验证用户名是否存在checkUser
		public String checkUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException{
			 PrintWriter pw=resp.getWriter();
		     String username=req.getParameter("name");
		     //调用业务层对象
		     UserService userService = new UserServiceImp();
		     User user = userService.checkUser(username);
		        //判断用户是否存在
		        if(user !=null){
		            pw.print(true);
		        }else{
		            pw.print(false);
		        }
			return null;
		}
	
}	
