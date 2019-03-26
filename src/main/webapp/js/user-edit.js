
$(document).ready(function() {
	var action = $("body").attr("data-action");
	var userId = $("body").attr("data-userId");
	
	if (action == OperationType.VIEW) {
		disableControls(action);
		getUser(userId);
	}
	else if (action == OperationType.ADD) {
		document.getElementById("save_btn").onclick = function() { createUser(); };
	}
	else if (action == OperationType.UPDATE) {
		disableControls(action);
		getUser(userId);
		document.getElementById("save_btn").onclick = function() { updateUser(userId); };
	}
});

function disableControls(action) {
	document.getElementById("confirmed_password_label").style.display = "none";
	document.getElementById("confirmed_password_input").setAttribute("type", "hidden");
	
	if (action == OperationType.VIEW) {
		document.getElementById("save_btn").style.display = "none";
		document.getElementById("save_btn").setAttribute("type", "hidden");
	}
	
	// action can only be VIEW or UPDATE
	document.getElementById("log_name_input").disabled = true;
	document.getElementById("log_password_input").disabled = true;
	document.getElementById("confirmed_password_input").disabled = true;
	document.getElementById("user_name_input").disabled = true;
	document.getElementById("telephone_number_input").disabled = action == OperationType.VIEW ? true : false;
		
	// document.getElementById("role_combobox").disabled = true;
	// document.getElementById("role_combobox").style.display = "none";
	
	// document.getElementById("school_combobox").disabled = true;
	// document.getElementById("school_combobox").style.display = "none";
	
	// document.getElementById("branch_school_combobox").disabled =true;
	// document.getElementById("branch_school_combobox").style.display = "none";
	
	// document.getElementById("group_combobox").disabled = true;
	// document.getElementById("group_combobox").style.display = "none";
}

function validate() {
	var action = $("body").attr("data-action");
	
	var logName = $("#log_name_input").val().trim();
	if (logName == "") {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: "用户名不能为空。"});
		return false;
	}

	if (action == OperationType.ADD) {
		var logPassword = $("#log_password_input").val().trim();
		if (logPassword == "") {
			bootbox.alert({closeButton: false, size: "small", title: "提示", message: "密码不能为空。"});
			return false;
		}

		var logConfirmedPassword = $("#confirmed_password_input").val().trim();
		if (logConfirmedPassword == "") {
			bootbox.alert({closeButton: false, size: "small", title: "提示", message: "确认密码不能为空。"});
			return false;
		}
		
		if (logPassword != logConfirmedPassword) {
			bootbox.alert({closeButton: false, size: "small", title: "提示", message: "两次输入的密码不相同。"});
			return false;
		}
	}

	var userName = $("#user_name_input").val().trim();
	if (userName == "") {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: "姓名不能为空。"});
		return false;
	}

	return true;
};

function getUser(userId) {
	$.ajax({
		url : userUrl + "/" + userId,
		type : 'GET',
		dataType : 'json',
		success : function(response) {
			if (0 == response.code) {
				showUser(response.data);
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

function createUser() {
	if (validate()) {
		var url = "";
		var roleType = $("body").attr("data-roleType");
		if (roleType == RoleTypeEnum.PRESIDENT) {
			url = presidentUrl; 
		}
		else if (roleType == RoleTypeEnum.DIRECTOR) {
			url = directorUrl + "?SchoolId=" + $("body").attr("data-schoolId");
		}
		else if (roleType == RoleTypeEnum.GROUP_LEADER) {
			url = groupLeaderUrl + "?BranchSchoolId=" + $("body").attr("data-branchSchoolId");
		}
		
		$.ajax({
			url : url,
			type : 'POST',
			dataType : 'json',
			contentType:"application/json; charset=utf-8",
			data : JSON.stringify(getNewUserJsonData()),
			success : function(response) {
				bootbox.alert({closeButton: false, size: "small", title: "提示", message: response.message + ' (' + response.code +')'});
			},
			error : function(msg, url, line) {
				bootbox.alert({closeButton: false, size: "small", title: "错误", message: 'msg = ' + msg + ', url = ' + url + ', line = ' + line });
			}
		});
	}
};

function updateUser(userId) {
	if (validate()) {
		$.ajax({
			url : userUrl + "/" + userId,
			type : 'PUT',
			dataType : 'json',
			contentType:"application/json; charset=utf-8",
			data : JSON.stringify(getUpdatedUserJsonData()),
			success : function(response) {
				bootbox.alert({closeButton: false, size: "small", title: "提示", message: response.message + ' (' + response.code +')'});
			},
			error : function(msg, url, line) {
				bootbox.alert({closeButton: false, size: "small", title: "错误", message: 'msg = ' + msg + ', url = ' + url + ', line = ' + line });
			}
		});
	}
};

function showUser(user) {
	document.getElementById("log_name_input").value = user.log_name;
	document.getElementById("log_password_input").value = "******"; // set fake password
	document.getElementById("user_name_input").value = user.name;
	document.getElementById("telephone_number_input").value = user.telephone_number != null ? user.telephone_number : " ";
	// document.getElementById("role_combobox").value = user.role_id;
	// document.getElementById("school_combobox").value = user.school_id != null ? user.school_id : "";
	// document.getElementById("branch_school_combobox").value = user.branch_school_id != null ? user.branch_school_id : "";
	// document.getElementById("group_combobox").value = user.group_id != null ? user.group_id : "";
};

function getNewUserJsonData() {
	var userData = {};
	
	userData["log_name"] = $("#log_name_input").val().trim();
	userData["password"] = $("#log_password_input").val().trim();
	userData["name"] = $("#user_name_input").val().trim();
	// userData["role_id"] = parseInt($("#role_combobox").val());
	
	var telephoneNumber = document.getElementById("telephone_number_input").value.trim();
	userData["telephone_number"] = telephoneNumber != "" ? telephoneNumber : null;
	
	// var schoolId = document.getElementById("school_combobox").value.trim();
	// userData["school_id"] = schoolId != "" ? schoolId : null;
	
	// var branchSchoolId = document.getElementById("branch_school_combobox").value.trim();
	// userData["branch_school_id"] = branchSchoolId != "" ? branchSchoolId : null;
	
	// var groupId = document.getElementById("group_combobox").value.trim();
	// userData["group_id"] = groupId != "" ? groupId : null;
	
	userData["status"] = true;
	
	return userData;
}

function getUpdatedUserJsonData() {
	var userData = {};
	
	// userData["role_id"] = parseInt($("#role_combobox").val());
	
	var telephoneNumber = document.getElementById("telephone_number_input").value.trim();
	userData["telephone_number"] = telephoneNumber != "" ? telephoneNumber : null;
	
	// var schoolId = document.getElementById("school_combobox").value.trim();
	// userData["school_id"] = schoolId != "" ? schoolId : null;
	
	// var branchSchoolId = document.getElementById("branch_school_combobox").value.trim();
	// userData["branch_school_id"] = branchSchoolId != "" ? branchSchoolId : null;
	
	// var groupId = document.getElementById("group_combobox").value.trim();
	// userData["group_id"] = groupId != "" ? groupId : null;
	
	return userData;
}
