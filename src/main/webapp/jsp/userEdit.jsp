<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String action = request.getParameter("Action");
	String userId = request.getParameter("UserId");
	String roleType = request.getParameter("RoleType");
	String schoolId = request.getParameter("SchoolId");
	String branchSchoolId = request.getParameter("BranchSchoolId");
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>User Edit Page</title>

<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
<link rel="stylesheet" href="css/common.css" />

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>
<%@ include file= "../js/common/global_variables"%>
<script type="text/javascript" src="<%=basePath%>js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/user-edit.js"></script>
</head>

<body data-action="<%=action%>" data-userId="<%=userId%>" data-roleType="<%=roleType%>" data-schoolId="<%=schoolId%>" data-branchSchoolId="<%=branchSchoolId%>">
	<div class="container-fluid">
	<div class="row">
			<div class="col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row operation-toolbar">
							<div class="col-6">
								<span class="far fa-list-alt"></span> 用户信息
							</div>
							<div class="btn-group col-6 justify-content-end">
								<button type="button" class="btn btn-secondary btn-sm" id="save_btn">
									<span class="fas fa-pencil-alt"></span> 保存
								</button>
								<button type="button" class="btn btn-secondary btn-sm" id="return_btn" onclick="goBackToPrePage()">
									<span class="fas fa-pencil-alt"></span> 返回
								</button>
							</div>
						</div>
					</div>

					<div class="panel-body">
						<div class="row query-form">
							<form style="margin-bottom:1px; width:100%; height:93%">
								<div class="form-group">
								    <label class="col-sm-2 col-form-label" for="log_name_input">用户名</label>
								    <div class="col-sm-3">
										<input type="text" class="form-control mr-sm-2 mb-2 mb-sm-0" id="log_name_input" name="log_name" placeholder="输入用户名">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 col-form-label" for="log_password_input">密码</label>
									<label class="col-sm-3 col-form-label" for="confirmed_password_input" id="confirmed_password_label">确认密码</label>
									<div class="row " style="position: relative; margin: auto;">
										<div class="col-sm-3">
											<input type="password" class="form-control mr-sm-2 mb-2 mb-sm-0" id="log_password_input" name="log_password" placeholder="输入密码">
										</div>
										<div class="col-sm-3">
											<input type="password" class="form-control mr-sm-2 mb-2 mb-sm-0" id="confirmed_password_input" name="confirmed_password" placeholder="再次输入密码">
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 col-form-label" for="user_name_input">姓名</label>
									<div class="col-sm-3">
										<input type="text" class="form-control mr-sm-2 mb-2 mb-sm-0" id="user_name_input" name="user_name" placeholder="输入姓名">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 col-form-label" for="telephone_number_input">电话号码</label>
									<div class="col-sm-3">
										<input type="text" class="form-control mr-sm-2 mb-2 mb-sm-0" id="telephone_number_input" name="telephone_number" placeholder="输入电话号码">
									</div>
								</div>
								
								<!-- <div class="form-group">
									<label class="col-sm-2 col-form-label" for="role">职位</label>
									<div class="col-sm-3">
										<select class="combobox form-control mr-sm-2 mb-2 mb-sm-0" data-style="btn-primary" id="role_combobox">
											<option value="0">系统管理员</option>
											<option value="1">校长</option>
											<option value="2">园长</option>
											<option value="3">老师</option>
										</select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 col-form-label" for="school">幼儿园</label>
									<label class="col-sm-3 col-form-label" for="branch_school">分园</label>
									<label class="col-sm-3 col-form-label" for="group">班级</label>
									<div class="row " style="position: relative; margin: auto;">
										<div class="col-sm-3">
											<select class="combobox form-control mr-sm-2 mb-2 mb-sm-0" data-style="btn-primary" id="school_combobox">
												<option></option>
											</select>
										</div>
										<div class="col-sm-3">
											<select class="combobox form-control mr-sm-2 mb-2 mb-sm-0" data-style="btn-primary" id="branch_school_combobox">
												<option></option>
											</select>
										</div>
										<div class="col-sm-3">
											<select class="combobox form-control mr-sm-2 mb-2 mb-sm-0" data-style="btn-primary" id="group_combobox">
												<option></option>
											</select>
										</div>
									</div>
								</div> -->
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
