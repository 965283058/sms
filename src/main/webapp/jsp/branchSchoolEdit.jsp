<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String action = request.getParameter("Action");
	String schoolId = request.getParameter("SchoolId");
	String branchSchoolId = request.getParameter("BranchSchoolId");
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>School Edit Page</title>

<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.2/bootstrap-table.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/css/tempusdominus-bootstrap-4.min.css" />

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.2/bootstrap-table.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.2/locale/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.23.0/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/js/tempusdominus-bootstrap-4.min.js"></script>
<%@ include file= "../js/common/global_variables"%>
<script type="text/javascript" src="<%=basePath%>js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/table-common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/branch-school-edit.js"></script>
</head>

<body data-action="<%=action%>" data-schoolId="<%=schoolId%>" data-branchSchoolId="<%=branchSchoolId%>">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12" id="basic_information_div">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row operation-toolbar">
							<div class="col-6">
								<span class="far fa-list-alt"></span> 分园信息
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
							<form id="basic_information_form" style="margin-bottom:1px; width:100%; height:auto">
								<div class="form-group">
									<label class="col-sm-3 col-form-label" for="branch_school_name_input">分园名称</label>
									<label class="col-sm-3 col-form-label" for="telephone_number_input">联系电话</label>
									<label class="col-sm-2 col-form-label" for="director_combobox">园长</label>
									<div class="row " style="position: relative; margin: auto;">
										<div class="col-sm-3">
											<input type="text" class="form-control mr-sm-2 mb-2 mb-sm-0" id="branch_school_name_input" name="branch_school_name" placeholder="输入分园名称">
										</div>
										<div class="col-sm-3">
											<input type="text" class="form-control mr-sm-2 mb-2 mb-sm-0" id="telephone_number_input" name="telephone_number" placeholder="输入电话号码">
										</div>
										<div class="col-sm-3">
											<select class="combobox form-control mr-sm-2 mb-2 mb-sm-0" data-style="btn-primary" id="director_combobox">
												<option></option>
											</select>
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 col-form-label" for="address_input">分园地址</label>
									<div class="col-sm-9">
										<input type="text" class="form-control mr-sm-2 mb-2 mb-sm-0" id="address_input" name="address" placeholder="输入分园地址">
									</div>
								</div>
								
								<div class="form-group form-inline">
									<label class="col-form-label" style="padding-left: 15px; padding-right: 20px;">监控开放日期</label>
									<label class="checkbox-inline">
       									<input type="checkbox" id="monday_checkbox" value="MONDAY"> 星期一
    								</label>
    								<label class="checkbox-inline">
        								<input type="checkbox" id="tuesday_checkbox" value="TUESDAY"> 星期二
    								</label>
    								<label class="checkbox-inline">
        								<input type="checkbox" id="wednesday_checkbox" value="WEDNESDAY"> 星期三
    								</label>
    								<label class="checkbox-inline">
        								<input type="checkbox" id="thursday_checkbox" value="THURSDAY"> 星期四
    								</label>
    								<label class="checkbox-inline">
        								<input type="checkbox" id="friday_checkbox" value="FRIDAY"> 星期五
    								</label>
    								<label class="checkbox-inline">
        								<input type="checkbox" id="saturday_checkbox" value="SATURDAY"> 星期六
    								</label>
    								<label class="checkbox-inline">
        								<input type="checkbox" id="sunday_checkbox" value="SUNDAY"> 星期日
    								</label>
								</div>
								
								<div class="form-group form-inline">
									<label class="col-form-label" style="padding-left:15px;">监控开始时间</label>
									<div class="col-sm-2 input-group date" id="monitor_start_timepicker" data-target-input="nearest">
										<input type="text" class="form-control datetimepicker-input" data-target="#monitor_start_timepicker"/>
										<div class="input-group-append" data-target="#monitor_start_timepicker" data-toggle="datetimepicker">
											<div class="input-group-text"><i class="fa fa-clock-o"></i></div>
										</div>
									</div>
									
									<label class="col-form-label" style="padding-left:15px;">监控结束时间</label>
									<div class="col-sm-2 input-group date" id="monitor_end_timepicker" data-target-input="nearest">
										<input type="text" class="form-control datetimepicker-input" data-target="#monitor_end_timepicker"/>
										<div class="input-group-append" data-target="#monitor_end_timepicker" data-toggle="datetimepicker">
											<div class="input-group-text"><i class="fa fa-clock-o"></i></div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-sm-12" id="extended_information_div">
				<div class="query-form" style="margin-bottom:1px; width:100%; height:auto">
					<ul class="nav nav-tabs" id="school_information_tab" role="tablist">
					    <li class="nav-item">
					        <a class="nav-link active" id="information_tab" data-toggle="tab" href="#information" role="tab" aria-controls="information"aria-selected="true">信息宣传</a>
					    </li>
					    <li class="nav-item">
					        <a class="nav-link" id="study_fee_tab" data-toggle="tab" href="#study_fee" role="tab" aria-controls="profile" aria-selected="study_fee">缴费标准</a>
					    </li>
					    
					</ul>
					<div class="tab-content border border-top-0" id="school_information_tab_content" >
					    <div class="tab-pane fade show active" id="information" role="tabpanel" aria-labelledby="information_tab">
							<div class="col-sm-12" style="padding-left: 0px; padding-right: 0px;">
								<div class="panel panel-default">
									<div class="panel-heading">
										<div class="row operation-toolbar" style="margin-left: 0px;	margin-right: 0px;">
											<div class="col-6">
												<span class="far fa-list-alt"></span> 信息宣传列表
											</div>
											<div class="btn-group col-6 justify-content-end">
												<button type="button" class="btn btn-secondary btn-sm" id="add_btn" data-permission="add" onclick="editInformation(OperationType.ADD)">
													<span class="fas fa-pencil-alt"></span> 新建
												</button>
												<button type="button" class="btn btn-secondary btn-sm" id="edit_btn" data-permission="update" onclick="editInformation(OperationType.UPDATE)">
													<span class="fas fa-pencil-alt"></span> 修改
												</button>
												<button type="button" class="btn btn-secondary btn-sm" id="delete_btn" data-permission="delete" onclick="editInformation(OperationType.DELETE)">
													<span class="fas fa-pencil-alt"></span> 删除
												</button>
												<button type="button" class="btn btn-secondary btn-sm" id="view_btn" data-permission="view" onclick="editInformation(OperationType.VIEW)">
													<span class="fas fa-pencil-alt"></span> 查看
												</button>
											</div>
										</div>
									</div>
				
									<div class="panel-body">
										<table id="information_table"></table>
									</div>
								</div>
							</div>
					    </div>
					    
					    <div class="tab-pane fade" id="study_fee" role="tabpanel" aria-labelledby="study_fee_tab">
					        <div class="col-sm-12" style="padding-left: 0px; padding-right: 0px;">
								<div class="panel panel-default">
									<div class="panel-heading">
										<div class="row operation-toolbar" style="margin-left: 0px;	margin-right: 0px;">
											<div class="col-6">
												<span class="far fa-list-alt"></span> 缴费标准列表
											</div>
											<div class="btn-group col-6 justify-content-end">
												<button type="button" class="btn btn-secondary btn-sm" id="add_btn" data-permission="add" onclick="editStudyFee(OperationType.ADD)">
													<span class="fas fa-pencil-alt"></span> 新建
												</button>
												<button type="button" class="btn btn-secondary btn-sm" id="edit_btn" data-permission="update" onclick="editStudyFee(OperationType.UPDATE)">
													<span class="fas fa-pencil-alt"></span> 修改
												</button>
												<button type="button" class="btn btn-secondary btn-sm" id="delete_btn" data-permission="delete" onclick="editStudyFee(OperationType.DELETE)">
													<span class="fas fa-pencil-alt"></span> 删除
												</button>
												<button type="button" class="btn btn-secondary btn-sm" id="view_btn" data-permission="view" onclick="editStudyFee(OperationType.VIEW)">
													<span class="fas fa-pencil-alt"></span> 查看
												</button>
											</div>
										</div>
									</div>
				
									<div class="panel-body">
										<table id="study_fee_table"></table>
									</div>
								</div>
							</div>
					    </div>
					</div>
				</div>
			</div>
		
		</div>
	</div>
</body>
</html>
