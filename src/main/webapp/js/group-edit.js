$(document).ready(function() {
	var action = $("body").attr("data-action");
	var schoolId = $("body").attr("data-schoolId");
	var branchSchoolId = $("body").attr("data-branchSchoolId");
	var groupId = $("body").attr("data-groupId");
	
	disableControls(action);
	
	initDayOfWeekDateTimePickers();
	
	// Initialize group leader name combo box
	var groupLeaders = getGroupLeaders(branchSchoolId);
	initGroupLeaderNameComboBox(groupLeaders);
	
	if (action == OperationType.ADD) {
		document.getElementById("save_btn").onclick = function() { createGroup(); };
		return;
	}
	
	if (action == OperationType.VIEW) {
		getGroup(groupId);
	}
	else if (action == OperationType.UPDATE) {
		getGroup(groupId);
		document.getElementById("save_btn").onclick = function() { updateGroup(groupId); };
	}
	
	// Reset Ids
	initTableQueryParameters();

	// Initialize tables
	init_information_table();
	init_study_fee_table();
});

function initDayOfWeekDateTimePickers() {
	$('#start_datepicker').datetimepicker({format: 'Y-MM-DD', useCurrent: true});
};

function disableControls(action) {
	if (action == OperationType.ADD) {
		document.getElementById("basic_information_form").style.height = "93%";
		document.getElementById("extended_information_div").style.display = "none";
		document.getElementById("extended_information_div").setAttribute("type", "hidden");
		return;
	}
	
	if (action == OperationType.VIEW) {
		document.getElementById("save_btn").style.display = "none";
		document.getElementById("save_btn").setAttribute("type", "hidden");
	}
	
	// action can only be VIEW or UPDATE
	document.getElementById("group_name_input").disabled = action == OperationType.VIEW ? true : false;
	document.getElementById("group_leader_combobox").disabled = action == OperationType.VIEW ? true : false;
	document.getElementsByClassName("datetimepicker-input")[0].disabled = action == OperationType.VIEW ? true : false;
};

function initGroupLeaderNameComboBox(groupLeaders) {
	if (groupLeaders.length > 0) {
		var groupLeaderNameMap = new Object();
		groupLeaders.forEach(element => { groupLeaderNameMap[element.id] = element.name;});
		if (groupLeaderNameMap != null) {
			var groupLeaderCombobox = document.getElementById("group_leader_combobox");
			for (var key in groupLeaderNameMap) {
				var option = document.createElement("option");
				option.value = key;
				option.text = groupLeaderNameMap[key];
				groupLeaderCombobox.add(option, null);
			};
		}
	}
};

function getGroup(groupId) {
	$.ajax({
		url : groupUrl + "/" + groupId,
		type : 'GET',
		dataType : 'json',
		success : function(response) {
			if (0 == response.code) {
				showGroup(response.data);
			}
			else {
				bootbox.alert({closeButton: false, size: "small", title: "提示", message: response.message + ' (' + response.code +')'});
			}
		},
		error : function(msg, url, line) {
			bootbox.alert({closeButton: false, size: "small", title: "错误", message: 'msg = ' + msg + ', url = ' + url + ', line = ' + line });
		}
	});
};

function createGroup() {
	if (validate()) {
		$.ajax({
			url : groupUrl,
			type : 'POST',
			dataType : 'json',
			contentType:"application/json; charset=utf-8",
			data : JSON.stringify(getGroupJsonData()),
			success : function(response) {
				bootbox.alert({closeButton: false, size: "small", title: "提示", message: response.message + ' (' + response.code +')'});
			},
			error : function(msg, url, line) {
				bootbox.alert({closeButton: false, size: "small", title: "错误", message: 'msg = ' + msg + ', url = ' + url + ', line = ' + line });
			}
		});
	}
};

function updateGroup(groupId) {
	if (validate()) {
		$.ajax({
			url : groupUrl + "/" + groupId,
			type : 'PUT',
			dataType : 'json',
			contentType:"application/json; charset=utf-8",
			data : JSON.stringify(getGroupJsonData()),
			success : function(response) {
				bootbox.alert({closeButton: false, size: "small", title: "提示", message: response.message + ' (' + response.code +')'});
			},
			error : function(msg, url, line) {
				bootbox.alert({closeButton: false, size: "small", title: "错误", message: 'msg = ' + msg + ', url = ' + url + ', line = ' + line });
			}
		});
	}
};

function validate() {
	var name = $("#group_name_input").val().trim();
	if (name == "") {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: "班级名称不能为空。"});
		return false;
	}

	return true;
};

function setGroupStartDate(startDate) {
	if (startDate != null) {
		$('#start_datepicker').data('datetimepicker').date(new Date(startDate));
	}
	else {
		$('#start_datepicker').data('datetimepicker').clear();
	}
};

function getGroupStartDateString() {
	var date = $('#start_datepicker').data('datetimepicker').date();
	return moment(date).format('Y-MM-DD');
};

function showGroup(group) {
	document.getElementById("group_name_input").value = group.name;
	document.getElementById("group_leader_combobox").value = group.group_leader_id != null ? group.group_leader_id : "";
	setGroupStartDate(group.start_date);
};

function getGroupJsonData() {
	var groupData = {};
	
	groupData["name"] = $("#group_name_input").val().trim();
	
	var groupLeaderId = document.getElementById("group_leader_combobox").value.trim();
	groupData["group_leader_id"] = groupLeaderId != "" ? groupLeaderId : null;
	
	groupData["branch_school_id"] = $("body").attr("data-branchSchoolId");
	groupData["start_date"] = getGroupStartDateString();
	
	return groupData;
};

function statusFormatter(value) {
	// TBD
};

function init_information_table() {
	$('#information_table').bootstrapTable({
		height : '45%',
		url : "http://localhost:8080/KindergartenManagementSystem/School/{schoolId}/Informations", //请求后台的URL（*）
		method : 'get', //请求方式（*）
		dataType : 'json',
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortable : false, //是否启用排序
		sortOrder : "asc", //排序方式		
		sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, //初始化加载第一页，默认第一页
		pageSize : 10, //每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
		strictSearch : true,
		clickToSelect : true, //是否启用点击选中行
		height : 250, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "id", //每一行的唯一标识，一般为主键列
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		paginationLoop : false,
		paginationPreText : "上一页",
		paginationNextText : "下一页",
		queryParams : queryParams, //传递参数（*）
		responseHandler : responseHandler, //接受后台传过来的值进行绑定处理的函数
		onClickRow : onClickRow,
		onCheck : onCheck,
		columns : [
			{
				radio : true
			},
			{
				title : '序号',
				width : 50,
				align : "center",
				formatter : function(value, row, index) {
					return index + 1;
				}
			},
			{
				title : '标题',
				field : 'title'
			},
			{
				title : '类别',
				field : 'category'
			},
			{
				title : '建立时间',
				field : 'create_datetime'
			},
			{
				title : '状态',
				field : 'status',
				formatter : statusFormatter 
			}
		]
	});
	$(".fixed-table-container").css({
		height : '45%'
	});
	$('#information_table').bootstrapTable('resetView', {
		height : '45%'
	});
};

function init_study_fee_table() {
	$('#study_fee_table').bootstrapTable({
		height : '45%',
		url : "http://localhost:8080/KindergartenManagementSystem/School/{schoolId}/Informations", //请求后台的URL（*）
		method : 'get', //请求方式（*）
		dataType : 'json',
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortable : false, //是否启用排序
		sortOrder : "asc", //排序方式		
		sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, //初始化加载第一页，默认第一页
		pageSize : 10, //每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
		strictSearch : true,
		clickToSelect : true, //是否启用点击选中行
		height : 250, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "id", //每一行的唯一标识，一般为主键列
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		paginationLoop : false,
		paginationPreText : "上一页",
		paginationNextText : "下一页",
		queryParams : queryParams, //传递参数（*）
		responseHandler : responseHandler, //接受后台传过来的值进行绑定处理的函数
		onClickRow : onClickRow,
		onCheck : onCheck,
		columns : [
			{
				radio : true
			},
			{
				title : '序号',
				width : 50,
				align : "center",
				formatter : function(value, row, index) {
					return index + 1;
				}
			},
			{
				title : '费用名称',
				field : 'title'
			},
			{
				title : '类别',
				field : 'category'
			},
			{
				title : '金额',
				field : 'price'
			},
			{
				title : '建立时间',
				field : 'create_datetime'
			},
			{
				title : '状态',
				field : 'status',
				formatter : statusFormatter 
			}
		]
	});
	$(".fixed-table-container").css({
		height : '45%'
	});
	$('#study_fee_table').bootstrapTable('resetView', {
		height : '45%'
	});
};
