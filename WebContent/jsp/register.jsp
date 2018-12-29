<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head></head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员注册</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font {
    color: #3164af;
    font-size: 18px;
    font-weight: normal;
    padding: 0 10px;
}
 </style>
 
 <!-- 引入js代码域 -->
<script type="text/javascript">
$(function(){
		$("#fm").submit(function(){
			if($("#inputPassword3").val()==""){
				alert("密码不能为空");
				return false;
			}else if($("#confirmpwd").val()==""){
				alert("确认密码不能为空");
				return false;
			}else if($("#inputPassword3").val() != $("#confirmpwd").val()){
				alert("两次密码不一致,请重新输入");
				return false;
			}else if($("#tel").val()==""){
				alert("手机号码不能为空");
				return false;
			}else if($("#usercaption").val()==""){
				alert("请输入真实姓名");
				return false;
			}else if($("#time").val()==""){
				alert("请选择出生日期");
				return false;
			}else if(req.responseText=="true"){
				alert("该用户名已被注册,请重新输入!");
				return false;
			}else if($("#username").val()==""){
				alert("用户名不能为空!");
				return false;
			}else{
				return true;
			}
		})
	})
	
	/* 判断用户名是否被注册 */
	
	  //1.获取XMLHttpRequest对象
 var req=getXMLHttpRequest();

window.onload=function()
{
    var nameElement=document.getElementsByName("username")[0];
    //为昵称选项注册onblur事件
    nameElement.onblur=function()
    {
        var name=this.value;
       /*   //1.获取XMLHttpRequest对象
        var req=getXMLHttpRequest();  */
        //4.处理响应结果
        req.onreadystatechange=function(){
            if(req.readyState==4){//XMLHttpRequest对象读取成功
                if(req.status==200){//服务器相应正常
                    var msg=document.getElementById("msg");
                    //根据返回的结果显示不同的信息
                    if(req.responseText=="true"){
                        msg.innerHTML="<font color='red'>该用户名已被注册</font>";
                    }else{
                        msg.innerHTML="<font color='green'>该用户名可以使用</font>";
                    }
                }
            }
        }
        //2.建立一个连接
        req.open("get","${pageContext.request.contextPath}/UserServlet?method=checkUser&name="+name);
        //3.发送get请求
        req.send(null);
    }
}

function getXMLHttpRequest(){
    var xmlhttp;
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        } else {// code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        return xmlhttp;
} 	
	
</script>

</head>
<body>

			<!--
            	描述：菜单栏
            -->
			<%@include file="/jsp/header.jsp" %>
			</div>
		
		
			 <div class="container"
				style="width:100%;background:url('${pageContext.request.contextPath}/img/regist_bg.jpg');">
				<div class="row">

			<div class="col-md-2"></div>

			<div class="col-md-8"
				style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<font>会员注册</font>USER REGISTER
				<form class="form-horizontal" id="fm" style="margin-top: 5px;"
					action="${pageContext.request.contextPath}/UserServlet?method=userRegist"
					method="post">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label> 
							<span id="msg" style="color: red;"></span>  <!--  设置提示信息,用户名是否被注册 -->
						<div class="col-sm-6">
							<input type="text" name="username" class="form-control"
								id="username" placeholder="请输入用户名">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" name="password" class="form-control"
								id="inputPassword3" placeholder="请输入密码">
						</div>
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="confirmpwd"
								placeholder="请输入确认密码">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" name="email" class="form-control"
								id="inputEmail3" placeholder="Email">
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">电话</label>
						<div class="col-sm-6">
							<input type="number" name="telephone" class="form-control"
								id="tel" placeholder="Telephone">
						</div>
					</div>

					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" name="name" class="form-control"
								id="usercaption" placeholder="请输入姓名">
						</div>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> <input type="radio"
								name="sex" id="inlineRadio1" value="男" checked="checked">
								男
							</label> <label class="radio-inline"> <input type="radio"
								name="sex" id="inlineRadio2" value="女"> 女
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" name="birthday" class="form-control" id="time">
						</div>
					</div>

					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-3">
							<input type="text" class="form-control">

						</div>
						<div class="col-sm-2">
							<img src="${pageContext.request.contextPath}/img/captcha.jhtml" />
						</div>

					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" width="100" value="注册" name="submit"
								border="0"
								style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>


	<!-- 引入页脚部分 -->
	<%@include file="/jsp/footer.jsp" %>

</body>
</html>




