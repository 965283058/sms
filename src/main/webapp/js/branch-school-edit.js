$(document).ready(function() {
	var action = $("body").attr("data-action");
	var schoolId = $("body").attr("data-schoolId");
	var branchSchoolId = $("body").attr("data-branchSchoolId");
	
	disableControls(action);
	
	initDayOfWeekDateTimePickers();
	
	// Initialize director name combo box
	var directors = getDirectors(schoolId);
	initDirectorNameComboBox(directors);
	
	if (action == OperationType.ADD) {
		initDayOfWeekCheckBoxes();
		document.getElementById("save_btn").onclick = function() { createBranchSchool(); };
		return;
	}
	
	if (action == OperationType.VIEW) {
		getBranchSchool(branchSchoolId);
	}
	else if (action == OperationType.UPDATE) {
		getBranchSchool(branchSchoolId);
		document.getElementById("save_btn").onclick = function() { updateBranchSchool(branchSchoolId); };
	}
	
	// Reset Ids
	initTableQueryParameters();

	// Initialize tables
	init_information_table();
	init_study_fee_table();
});

function initDayOfWeekDateTimePickers() {
	var startTime = new Date(2011, 8, 2, 8, 0, 0, 0);
	var endTime = new Date(2011, 8, 2, 18, 0, 0, 0);
	$('#monitor_start_timepicker').datetimepicker({format: 'HH:mm', defaultDate: startTime});
	$('#monitor_end_timepicker').datetimepicker({format: 'HH:mm', defaultDate: endTime});
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
	document.getElementById("branch_school_name_input").disabled = action == OperationType.VIEW ? true : false;
	document.getElementById("telephone_number_input").disabled = action == OperationType.VIEW ? true : false;
	document.getElementById("address_input").disabled = action == OperationType.VIEW ? true : false;
	document.getElementById("director_combobox").disabled = action == OperationType.VIEW ? true : false;
	document.getElementById("monday_checkbox").disabled = action == OperationType.VIEW ? true : false;
	document.getElementById("tuesday_checkbox").disabled = action == OperationType.VIEW ? true : false;
	document.getElementById("wednesday_checkbox").disabled = action == OperationType.VIEW ? true : false;
	document.getElementById("thursday_checkbox").disabled = action == OperationType.VIEW ? true : false;
	document.getElementById("friday_checkbox").disabled = action == OperationType.VIEW ? true : false;
	document.getElementById("saturday_checkbox").disabled = action == OperationType.VIEW ? true : false;
	document.getElementById("sunday_checkbox").disabled = action == OperationType.VIEW ? true : false;
	
	var dateTimePickers = document.getElementsByClassName("datetimepicker-input");
	for (var dateTimePicker of dateTimePickers) {
		dateTimePicker.disabled = action == OperationType.VIEW ? true : false;
	}
};

function initDirectorNameComboBox(directors) {
	if (directors.length == 0)
		return;
	
	var directorIdNameMap = new Object();
	directors.forEach(element => { directorIdNameMap[element.id] = element.name;});
	if (directorIdNameMap != null) {
		var directorCombobox = document.getElementById("director_combobox");
		for (var key in directorIdNameMap) {
			var option = document.createElement("option");
			option.value = key;
			option.text = directorIdNameMap[key];
			directorCombobox.add(option, null);
		};
	}
};

function initDayOfWeekCheckBoxes() {
	document.getElementById("monday_checkbox").checked = true;
	document.getElementById("tuesday_checkbox").checked = true;
	document.getElementById("wednesday_checkbox").checked = true;
	document.getElementById("thursday_checkbox").checked = true;
	document.getElementById("friday_checkbox").checked = true;
	document.getElementById("saturday_checkbox").checked = false;
	document.getElementById("sunday_checkbox").checked = false;
};

function getBranchSchool(branchSchoolId) {
	$.ajax({
		url : branchSchoolUrl + "/" + branchSchoolId,
		type : 'GET',
		dataType : 'json',
		success : function(response) {
			if (0 == response.code) {
				showBranchSchool(response.data);
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

function createBranchSchool(schoolId) {
	if (validate()) {
		$.ajax({
			url : branchSchoolUrl,
			type : 'POST',
			dataType : 'json',
			contentType:"application/json; charset=utf-8",
			data : JSON.stringify(getBranchSchoolJsonData()),
			success : function(response) {
				bootbox.alert({closeButton: false, size: "small", title: "提示", message: response.message + ' (' + response.code +')'});
			},
			error : function(msg, url, line) {
				bootbox.alert({closeButton: false, size: "small", title: "错误", message: 'msg = ' + msg + ', url = ' + url + ', line = ' + line });
			}
		});
	}
};

function updateBranchSchool(branchSchoolId) {
	if (validate()) {
		$.ajax({
			url : branchSchoolUrl + "/" + branchSchoolId,
			type : 'PUT',
			dataType : 'json',
			contentType:"application/json; charset=utf-8",
			data : JSON.stringify(getBranchSchoolJsonData()),
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
	var action = $("body").attr("data-action");
	
	var name = $("#branch_school_name_input").val().trim();
	if (name == "") {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: "分园名称不能为空。"});
		return false;
	}

	return true;
};

function setDayOfWeekCheckBoxes(daysOfWeek) {
	if (daysOfWeek != null && daysOfWeek.length > 0) {
		daysOfWeek.forEach(d => {
			if (d == "MONDAY") {
				document.getElementById("monday_checkbox").checked = true;
			}
			else if (d == "TUESDAY") {
				document.getElementById("tuesday_checkbox").checked = true;
			}
			if (d == "WEDNESDAY") {
				document.getElementById("wednesday_checkbox").checked = true;
			}
			else if (d == "THURSDAY") {
				document.getElementById("thursday_checkbox").checked = true;
			}
			if (d == "FRIDAY") {
				document.getElementById("friday_checkbox").checked = true;
			}
			else if (d == "SATURDAY") {
				document.getElementById("saturday_checkbox").checked = true;
			}
			if (d == "SUNDAY") {
				document.getElementById("sunday_checkbox").checked = true;
			}
		});
	}
};

function setMonitorStartTime(startTime) {
	if (startTime != null) {
		$('#monitor_start_timepicker').data('datetimepicker').date(new Date("2011/08/02 " + startTime));
	}
	else {
		$('#monitor_start_timepicker').data('datetimepicker').clear();
	}
};

function setMonitorEndTime(endTime) {
	if (endTime != null) {
		$('#monitor_end_timepicker').data('datetimepicker').date(new Date("2011/08/02 " + endTime));
	}
	else {
		$('#monitor_end_timepicker').data('datetimepicker').clear();
	}
};

function getDaysOfWeek() {
	var daysOfWeek = "[";
	if (document.getElementById("monday_checkbox").checked == true) {
		daysOfWeek += "\"MONDAY\",";
	}
	if (document.getElementById("tuesday_checkbox").checked == true) {
		daysOfWeek += "\"TUESDAY\",";
	}
	if (document.getElementById("wednesday_checkbox").checked == true) {
		daysOfWeek += "\"WEDNESDAY\",";
	}
	if (document.getElementById("thursday_checkbox").checked == true) {
		daysOfWeek += "\"THURSDAY\",";
	}
	if (document.getElementById("friday_checkbox").checked == true) {
		daysOfWeek += "\"FRIDAY\",";
	}
	if (document.getElementById("saturday_checkbox").checked == true) {
		daysOfWeek += "\"SATURDAY\",";
	}
	if (document.getElementById("sunday_checkbox").checked == true) {
		daysOfWeek += "\"SUNDAY\",";
	}
	if (daysOfWeek.charAt(daysOfWeek.length - 1) == ",") {
		daysOfWeek = daysOfWeek.substr(0, daysOfWeek.length - 1);
	}
	daysOfWeek += "]";
	
	return daysOfWeek;
};

function getMonitorStartTimeString() {
	var date = $('#monitor_start_timepicker').data('datetimepicker').date();
	return moment(date).format('HH:mm:ss');
};

function getMonitorEndTimeString() {
	var date = $('#monitor_end_timepicker').data('datetimepicker').date();
	return moment(date).format('HH:mm:ss');
};

function showBranchSchool(branchSchool) {
	document.getElementById("branch_school_name_input").value = branchSchool.name;
	document.getElementById("telephone_number_input").value = branchSchool.telephone_number != null ? branchSchool.telephone_number : " ";
	document.getElementById("address_input").value = branchSchool.address;
	document.getElementById("director_combobox").value = branchSchool.director_id != null ? branchSchool.director_id : "";
	
	setDayOfWeekCheckBoxes(branchSchool.monitor_open_days_of_week);
	setMonitorStartTime(branchSchool.monitor_start_time);
	setMonitorEndTime(branchSchool.monitor_end_time);
};

function getBranchSchoolJsonData() {
	var branchSchoolData = {};
	
	branchSchoolData["name"] = $("#branch_school_name_input").val().trim();
	
	var telephoneNumber = document.getElementById("telephone_number_input").value.trim();
	branchSchoolData["telephone_number"] = telephoneNumber != "" ? telephoneNumber : null;
	
	var address = document.getElementById("address_input").value.trim();
	branchSchoolData["address"] = address != "" ? address : null;
	
	var directorId = document.getElementById("director_combobox").value.trim();
	branchSchoolData["director_id"] = directorId != "" ? directorId : null;
	
	branchSchoolData["school_id"] = $("body").attr("data-schoolId");
	
	branchSchoolData["monitor_open_days_of_week"] = getDaysOfWeek();
	branchSchoolData["monitor_start_time"] = getMonitorStartTimeString();
	branchSchoolData["monitor_end_time"] = getMonitorEndTimeString();
	
	return branchSchoolData;
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
