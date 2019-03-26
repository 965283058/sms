<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>Group Management Page</title>

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
<script type="text/javascript" src="<%=basePath%>js/common/table.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common/group-management-common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/group-management.js"></script>
</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row query-form">
							<form action="javascript:void(0);" class="form-inline" style="margin-bottom:1px;">
								<label class="mr-sm-2 mb-0" for="school_combobox">幼儿园</label>
								<select class="combobox form-control mr-sm-2 mb-2 mb-sm-0" style="width: 200px" data-style="btn-primary" id="school_combobox" onchange="onSchoolChange(this)"></select>
								
								<label class="mr-sm-2 mb-0" for="branch_school_combobox">分园</label>
								<select class="combobox form-control mr-sm-2 mb-2 mb-sm-0" style="width: 200px" data-style="btn-primary" id="branch_school_combobox" onchange="onBranchSchoolChange(this)"></select>
								
								<label class="mr-sm-2 mb-0" for="group_name_input">班级名称</label> 
								<input type="text" class="form-control mr-sm-2 mb-2 mb-sm-0" id="group_name_input" name="group_name">
								<button type="button" class="btn btn-default mt-2 mt-sm-0 btn-sm" onclick="search()">查询</button>
							</form>
						</div>
					</div>

					<div class="panel-body">
						<div class="row operation-toolbar">
							<div class="col-6">
								<span class="far fa-list-alt"></span> 班级列表
							</div>
							<div class="btn-group col-6 justify-content-end">
								<button type="button" class="btn btn-secondary btn-sm" id="add_btn" data-permission="add" onclick="editGroup(OperationType.ADD)">
									<span class="fas fa-pencil-alt"></span> 新建
								</button>
								<button type="button" class="btn btn-secondary btn-sm" id="edit_btn" data-permission="update" onclick="editGroup(OperationType.UPDATE)">
									<span class="fas fa-pencil-alt"></span> 修改
								</button>
								<button type="button" class="btn btn-secondary btn-sm" id="delete_btn" data-permission="delete" onclick="editGroup(OperationType.DELETE)">
									<span class="fas fa-pencil-alt"></span> 删除
								</button>
								<button type="button" class="btn btn-secondary btn-sm" id="view_btn" data-permission="view" onclick="editGroup(OperationType.VIEW)">
									<span class="fas fa-pencil-alt"></span> 查看
								</button>
							</div>
						</div>
						
						<table id="group_table"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
