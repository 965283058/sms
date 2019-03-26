<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>User Management Page</title>

<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.2/bootstrap-table.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
<link rel="stylesheet" href="css/common.css" />

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.2/bootstrap-table.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.2/locale/bootstrap-table-zh-CN.js"></script>
<%@ include file= "../js/common/global_variables"%>
<script type="text/javascript" src="<%=basePath%>js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/table-common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/user-management.js"></script>
</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row operation-toolbar">
							<div class="col-6">
								<span class="far fa-list-alt"></span> 用户列表
							</div>
							<div class="btn-group col-6 justify-content-end">
								<button type="button" class="btn btn-secondary btn-sm" id="add_btn" data-permission="add" onclick="editUser(OperationType.ADD)">
									<span class="fas fa-pencil-alt"></span> 新建
								</button>
								<button type="button" class="btn btn-secondary btn-sm" id="edit_btn" data-permission="update" onclick="editUser(OperationType.UPDATE)">
									<span class="fas fa-pencil-alt"></span> 修改
								</button>
								<button type="button" class="btn btn-secondary btn-sm" id="disable_btn" data-permission="disable" onclick="editUser(OperationType.DISABLE)">
									<span class="fas fa-pencil-alt"></span> 禁用
								</button>
								<button type="button" class="btn btn-secondary btn-sm" id="delete_btn" data-permission="delete" onclick="editUser(OperationType.DELETE)">
									<span class="fas fa-pencil-alt"></span> 删除
								</button>
								<button type="button" class="btn btn-secondary btn-sm" id="view_btn" data-permission="view" onclick="editUser(OperationType.VIEW)">
									<span class="fas fa-pencil-alt"></span> 查看
								</button>
							</div>
						</div>
					</div>

					<div class="panel-body">
						<div class="row query-form justify-content-end">
							<form action="javascript:void(0);" class="form-inline" style="margin-bottom:1px;">
								<label class="mr-sm-2 mb-0" for="user_name_input">用户姓名</label> 
								<input type="text" class="form-control mr-sm-2 mb-2 mb-sm-0" id="user_name_input" name="user_name">
								<button type="button" class="btn btn-default mt-2 mt-sm-0 btn-sm" onclick="search()">查询</button>
							</form>
						</div>

						<table id="table"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
