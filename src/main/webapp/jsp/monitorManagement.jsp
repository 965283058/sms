<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>Monitor Management Page</title>

<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.2/bootstrap-table.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/css/tempusdominus-bootstrap-4.min.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/modal-common.css" />

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.2/bootstrap-table.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.2/locale/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.23.0/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/js/tempusdominus-bootstrap-4.min.js"></script>
<%@ include file= "../js/common/global_variables"%>
<script type="text/javascript" src="<%=basePath%>js/common/http-request-handler.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common/table.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common/single-file-uploader.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common/group-management-common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/monitor-management.js"></script>
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
								<select class="combobox form-control mr-sm-2 mb-2 mb-sm-0" style="width: 200px" data-style="btn-primary" id="school_combobox" onchange="onSchoolChangeEx(this)"></select>
								
								<label class="mr-sm-2 mb-0" for="branch_school_combobox">分园</label>
								<select class="combobox form-control mr-sm-2 mb-2 mb-sm-0" style="width: 200px" data-style="btn-primary" id="branch_school_combobox" onchange="onBranchSchoolChangeEx(this)"></select>
								
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
						</div>
						<table id="group_table"></table>
						
						<div class="row operation-toolbar">
							<div class="col-6">
								<span class="far fa-list-alt"></span> 监控列表
							</div>
							<div class="btn-group col-6 justify-content-end">
								<button type="button" class="btn btn-secondary btn-sm" id="add_btn" data-permission="add" onclick="editMonitor(OperationType.ADD)">
									<span class="fas fa-pencil-alt"></span> 新建
								</button>
								<button type="button" class="btn btn-secondary btn-sm" id="edit_btn" data-permission="update" onclick="editMonitor(OperationType.UPDATE)">
									<span class="fas fa-pencil-alt"></span> 修改
								</button>
								<button type="button" class="btn btn-secondary btn-sm" id="delete_btn" data-permission="delete" onclick="editMonitor(OperationType.DELETE)">
									<span class="fas fa-pencil-alt"></span> 删除
								</button>
								<button type="button" class="btn btn-secondary btn-sm" id="view_btn" data-permission="view" onclick="editMonitor(OperationType.VIEW)">
									<span class="fas fa-pencil-alt"></span> 查看
								</button>
							</div>
						</div>
						<table id="monitor_table"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="monitor_model">
	    <div class="modal-dialog modal-lg modal-dialog-centered">
	          <div class="modal-content">
	          		<!-- Modal Header -->
	          		<div class="modal-header">
	          			<h4 class="modal-title" style="text-align: center">监控信息</h4>
	          			<button type="button" class="close" data-dismiss="modal">&times;</button>
	          		</div>
	          		
	          		<!-- Modal body -->
	          		<div class="modal-body">
	          		<form id="modal_form" style="margin-bottom:1px; width:100%; height:auto">
	          			<div class="form-group form-inline">
	          				<label class="col-form-label" style="padding-left:15px; padding-right:15px;" for="monitor_name_input">监控名称</label>
	          				<div class="col-sm-8">
	          					<input type="text" style="width:100%" class="form-control" id="monitor_name_input" name="monitor_name" placeholder="输入监控名称">
	          				</div>
	          			</div>
	          			<div class="form-group form-inline">
	          				<label class="col-form-label" style="padding-left:15px; padding-right:15px;" for="monitor_group_name_input">监控班级</label>
	          				<div class="col-sm-8">
	          					<input type="text" style="width:100%" class="form-control" id="monitor_group_name_input" name="monitor_group_name">
	          				</div>
	          			</div>
	          			<div class="form-group form-inline">
	          				<label class="col-form-label" style="padding-left:15px; padding-right:15px;" for="monitor_branch_school_name_input">监控分园</label>
	          				<div class="col-sm-8">
	          					<input type="text" style="width:100%" class="form-control" id="monitor_branch_school_name_input" name="monitor_branch_school_name">
	          				</div>
	          			</div>
	          			<div class="form-group form-inline">
	          				<label class="col-form-label" style="padding-left:15px; padding-right:15px;" for="camera_address_input">视频地址</label>
	          				<div class="col-sm-8">
	          					<input type="text" style="width:100%" class="form-control" id="camera_address_input" name="camera_address" placeholder="输入视频地址">
	          				</div>
	          			</div>
	          			<div class="form-group form-inline">
	          				<label class="col-form-label" style="padding-left:15px; padding-right:15px;" for="monitor_description_input">监控说明</label>
	          				<div class="col-sm-8">
	          					<textarea style="width:100%" class="form-control" rows="4" id="monitor_description_input"></textarea>
	          				</div>
	          			</div>
	          			<div class="form-group form-inline">
	          				<label class="col-form-label" style="padding-left:15px; padding-right:15px;" for="monitor_photo_file">封面图片</label>
	          				<div class="col-sm-8">
	          					<div class="custom-file">
	          						<input type="file" class="custom-file-input" id="monitor_photo_file" name="filename">
	          						<label class="custom-file-label" style="text-align:left" for="monitor_photo_file">选择封面图片</label>
	          					</div>
	          				</div>
	          				<div id="progress-wrp">
								<div class="progress-bar"></div>
								<div class="status">0%</div>
							</div>
	          			</div>
	          			<div class="form-group form-inline">
							<label class="col-form-label" style="padding-left:15px; padding-right:15px;">安装日期</label>
							<div class="col-sm-4 input-group date" id="install_datepicker" data-target-input="nearest">
								<input type="text" class="form-control datetimepicker-input" data-target="#install_datepicker"/>
								<div class="input-group-append" data-target="#install_datepicker" data-toggle="datetimepicker">
									<div class="input-group-text"><i class="fa fa-clock-o"></i></div>
								</div>
							</div>
						</div>
					</form>
	          		</div>
	          		
	          		<!-- Modal footer -->
	          		<div class="modal-footer">
	          			<button type="button" class="btn btn-default" data-dismiss="modal">
	          				<i class="fa fa-times"></i> 取消
	          			</button>
	          			<button type="button" class="btn btn-primary" id="ok_btn">
	          				<i class="fa fa-check"></i> 确定
	          			</button>
	          		</div>
	          </div>
	    </div>
	</div>
</body>
</html>
