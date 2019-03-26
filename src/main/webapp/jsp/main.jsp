<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>Main Page</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://unpkg.com/metismenu/dist/metisMenu.min.css" />
<link rel="stylesheet" href="css/main.css" />

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/metismenu"></script>
<%@ include file= "../js/common/global_variables"%>
<script type="text/javascript" src="<%=basePath%>js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/main.js"></script>
</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top navbar-top"
		role="navigation" style="margin-bottom: 0">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="javascript:;"><b>后台管理系统</b></a>
			</div>
			<div>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a href="javascript:;" style="padding-left:0;padding-right:0;" title="">欢迎您：<span style="text-decoration: underline">XYZ</span></a>
					</li>
					<li class="dropdown">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" role="button" title="查看个人操作"> 
							<i class="fa fa-user fa-fw"></i> 
							<i class="fa fa-caret-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="javascript:;" id="modify_password_btn"><i class="fa fa-lock fa-fw"></i> 修改密码</a> 
								<a href="javascript:;" id="logout_btn"><i class="fa fa-sign-out fa-fw"></i> 退出系统</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="resizebar"></div>

	<div class="sidebar">
		<div class="sidebar-container">
			<ul class="nav nav-first-level metismenu" id="sidebar_menu">
				<li class="">
					<a class="parent" id="school_menu" target="main" href="javascript:;" data-level="1" aria-expanded="false">校务管理<span	class="glyphicon arrow"></span></a>
					<ul class="nav nav-second-level collapse in" style="height:0px;">
						<li>
							<a class="load-page" id="school_menu_item" target="main" href="#" data-url="jsp/schoolManagement.jsp" data-level="2">学校管理</a>
						</li>
						<li>
							<a class="load-page" id="subschool_menu_item" target="main" href="#" data-url="jsp/branchSchoolManagement.jsp" data-level="2">分园管理</a>
						</li>
						<li>
							<a class="load-page" id="group_menu_item" target="main" href="#" data-url="jsp/groupManagement.jsp" data-level="2">班级管理</a>
						</li>
						<li>
							<a class="load-page" id="monitor_menu_item" target="main" href="#" data-url="jsp/monitorManagement.jsp" data-level="2">监控管理</a>
						</li>
						<li>
							<a class="load-page" id="member_menu_item" target="main" href="#" data-url="jsp/memberManagement.jsp" data-level="2">会员管理</a>
						</li>
						<li>
							<a class="load-page" id="payment_menu_item" target="main" href="#" data-url="jsp/paymentManagement.jsp" data-level="2">缴费管理</a>
						</li>
					</ul>
				</li>
				
				<li class="">
					<a class="parent" id="information_menu" target="main" href="javascript:;" data-level="1" aria-expanded="false">通知管理<span class="glyphicon arrow"></span></a>
					<ul class="nav nav-second-level collapse in" style="height: 0px;">
						<li>
							<a class="load-page" id="school_information_menu_item" target="main" href="#" data-url="jsp/schoolInformationManagement.jsp" data-level="2">学校通知</a>
						</li>
						<li>
							<a class="load-page" id="branch_school_information_item" target="main" href="#" data-url="jsp/branchSchoolInformationManagement.jsp" data-level="2">分园通知</a>
						</li>
						<li>
							<a class="load-page" id="group_information_menu_item" target="main" href="#" data-url="jsp/groupInformationManagement.jsp" data-level="2">班级通知</a>
						</li>
					</ul>
				</li>
				
				<li class="">
					<a class="parent" id="study_menu" target="main" href="javascript:;" data-level="1" aria-expanded="false">学习管理<span class="glyphicon arrow"></span></a>
					<ul class="nav nav-second-level collapse in" style="height: 0px;">
						<li>
							<a class="load-page" id="subject_menu_item" target="main" href="#" data-url="jsp/subjectManagement.jsp" data-level="2">科目管理</a>
						</li>
						<li>
							<a class="load-page" id="course_menu_item" target="main" href="#" data-url="jsp/courseManagement.jsp" data-level="2">课程管理</a>
						</li>
						<li>
							<a class="load-page" id="courseware_menu_item" target="main" href="#" data-url="jsp/coursewareManagement.jsp" data-level="2">课件管理</a>
						</li>
					</ul>
				</li>
				
				<li>
					<a class="parent" id="user_menu" target="main" href="javascript:;" data-level="1">用户管理<span class="glyphicon arrow"></span></a>
					<ul class="nav nav-second-level collapse in" style="height: 0px;">
						<li>
							<a class="load-page" id="president_menu_item" target="main" href="#" data-url="jsp/presidentManagement.jsp" data-level="2">校长管理</a>
						</li>
						<li>
							<a class="load-page" id="director_menu_item" target="main" href="#" data-url="jsp/directorManagement.jsp" data-level="2">园长管理</a>
						</li>
						<li>
							<a class="load-page" id="group_leader_menu_item" target="main" href="#" data-url="jsp/groupLeaderManagement.jsp" data-level="2">班主任管理</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>

	<div id="page_wrapper" style="left:200px;">
		<div>
			<iframe name="content" id="content_frame" src="index.jsp"></iframe>
		</div>
		<div class="frame-mask hide"></div>
	</div>
	
	<div class="modal fade" id="change_password_model">
	    <div class="modal-dialog modal-dialog-centered">
	          <div class="modal-content">
	          		<!-- Modal Header -->
	          		<div class="modal-header">
	          			<h4 class="modal-title">修改密码</h4>
	          			<button type="button" class="close" data-dismiss="modal">&times;</button>
	          		</div>
	          		
	          		<!-- Modal body -->
	          		<div class="modal-body">
	          			<div class="form-group form-inline">
	          				<label class="col-form-label" style="padding-left:15px; padding-right:44px;" for="old_password_input">旧密码</label>
	          				<input type="password" class="form-control mr-sm-2 mb-2 mb-sm-0" id="old_password_input" name="old_password" placeholder="输入旧密码">
	          			</div>
	          			<div class="form-group form-inline">
	          				<label class="col-form-label" style="padding-left:15px; padding-right:44px;" for="new_password_input">新密码</label>
	          				<input type="password" class="form-control mr-sm-2 mb-2 mb-sm-0" id="new_password_input" name="new_password" placeholder="输入新密码">
	          			</div>
	          			<div class="form-group form-inline">
	          				<label class="col-form-label" style="padding-left:15px; padding-right:15px;" for="confirmed_new_password_input">确认新密码</label>
	          				<input type="password" class="form-control mr-sm-2 mb-2 mb-sm-0" id="confirmed_new_password_input" name="confirmed_new_password" placeholder="再次输入新密码">
	          			</div>
	          		</div>
	          		
	          		<!-- Modal footer -->
	          		<div class="modal-footer">
	          			<button type="button" class="btn btn-default" data-dismiss="modal">
	          				<i class="fa fa-times"></i> 取消
	          			</button>
	          			<button type="button" class="btn btn-primary" id="change_password_btn" onclick="changePassword()">
	          				<i class="fa fa-check"></i> 确定
	          			</button>
	          		</div>
	          </div>
	    </div>
	</div>
</body>
</html>
