<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()	+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>LoginPage</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/login.css" />

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>
<%@ include file= "../js/common/global_variables"%>
<script type="text/javascript" src="<%=basePath%>js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/logon.js"></script>
</head>

<body>
	<div class="container" style="margin-top: 200px;">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<form class="form " action="" method="post" id="longin_form">
					<div class="col-md-12 text-center text-info">
						<h2 style="color:white">用户登录</h2>
					</div>
					<div class="col-md-12 text-info">
						<h5 style="color:white">用户名</h5>
					</div>
					<div class="col-md-12">
						<input type="text" class="form-control" name="username" id="username_input" placeholder="请输入账户名" required autofocus />
					</div>
					<div class="col-md-12 text-info">
						<h5 style="color:white">密码</h5>
					</div>
					<div class="col-md-12">
						<input type="password" class="form-control" name="password" id="password_input" placeholder="请输入密码" required autofocus />
					</div>
					<div class="col-md-12 text-info">
						<h1></h1>
					</div>
					<div class="col-md-12">
						<button type="button" class="btn btn-success col-md-12" onclick="logon()">登录</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
