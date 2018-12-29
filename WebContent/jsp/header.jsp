<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Welcome</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
	</head>

	<body>
					<!--
            	描述：菜单栏
            -->
			<div class="container-fluid">
				<div class="col-md-4">
					<a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/img/logo2.png" /></a>
				</div>
				<div class="col-md-5">
					<img src="${pageContext.request.contextPath}/img/header.png" />
				</div>
				<div class="col-md-3" style="padding-top:20px">
					<ol class="list-inline">
					
						<c:if test="${empty loginUser }">
							<li><a href="${pageContext.request.contextPath}/UserServlet?method=loginUI">登录</a></li>
							<li><a href="${pageContext.request.contextPath}/UserServlet?method=registUI">注册</a></li>
						</c:if>			
					
						<c:if test="${not empty loginUser }">
							<li>欢迎您:${loginUser.username }</li>
							<li><a href="${pageContext.request.contextPath}/UserServlet?method=logOut">退出</a></li>
							<li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
							<li><a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrdersWithPage&num=1">我的订单</a></li>
						</c:if>
						
					</ol>
				</div>
			</div>
			
				<!--
            	描述：导航条
            -->
			<div class="container-fluid">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">首页</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav" id="myUL">
								
							<%-- 	<c:forEach items="${allCats }" var="c">
									<li><a href="#">${c.cname }</a></li>
								</c:forEach> --%>
							
							</ul>
							<form class="navbar-form navbar-right" role="search">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="Search">
								</div>
								<button type="submit" class="btn btn-default">Submit</button>
							</form>

						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>
			</div>
			
	</body>
<script>
	//向服务端发起ajax请求,服务端经过处理
	/* var url = "/store_v1/CategoryServlet";
	var obj = {"method":"findAllCats"}; */
	 $(function(){
			$.post("${pageContext.request.contextPath}/CategoryServlet",{"method":"findAllCats"},function(dt){
			 	//alert(dt);  //测试
			    $.each(dt,function(i,obj){
					var li="<li><a href='${pageContext.request.contextPath}/ProductServlet?method=findProductsByCidWithPage&num=1&cid="+obj.cid+"'>"+obj.cname+"</a></li>"; //href的单引号双引号问题
					$("#myUL").append(li);
				}); 
			 
			},"json");
		});	 
</script>


</html>