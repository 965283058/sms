// Local storage data
// user_name
// user_id

const RoleTypeEnum = {
	ADMINISTRATOR : 0,
	PRESIDENT : 1,
	DIRECTOR : 2,
	GROUP_LEADER : 3,
};

const RoleTypeDictionary = {
	0 : "系统管理员",
	1 : "校长",
	2 : "园长",
	3 : "老师",
};

const statusTypeDictionary = {
	0 : "禁用",
	1 : "启用",
};

const schoolTypeDictionary = {
	PUBLIC : 0,
	PRIVATE : 1,
};

const OperationType = {
	VIEW : 1,
	ADD : 2,
	UPDATE : 3,
	DELETE : 4,
	DISABLE : 5,
};

function clearLocalStorageData() {
	localStorage.removeItem("user_name");
	localStorage.removeItem("user_id");
};

function showDialog(response) {
	alert('提示（' + response.code + '）：' + response.message);
};

function goBackToPrePage() {
	javascript:history.back();
};

function getUserNameMapByRoleType(roleType) {
	var userNameMap = new Object();
	$.ajax({
		url : usersUrl + "?RoleType=" + roleType,
		type : 'GET',
		async: false,
		dataType : 'json',
		success : function(response) {
			if (0 == response.code) {
				response.data.forEach(element => { userNameMap[element.id] = element.name;});
			}
			else {
				bootbox.alert({closeButton: false, size: "small", title: "提示", message: response.message + ' (' + response.code +')'});
			}
		},
		error : function(msg, url, line) {
			bootbox.alert({closeButton: false, size: "small", title: "错误", message: 'msg = ' + msg + ', url = ' + url + ', line = ' + line });
		}
	});
	return userNameMap;
};

/*function getPresidents() {
	var a1 = await httpRequestHandler.syncGet(presidentsUrl);
	var yl = 10;
	var a2 = await httpRequestHandler.syncGet(presidentsUrl);
	return a2;
	var presidents = new Object();
	var promise =  await httpRequestHandler.syncGet(presidentsUrl);
	promise.then(function(response) {
		if (0 == response.code) {
			presidents = response.data;
		}
	});
	return presidents;
};*/

function getPresidents() {
	var presidents = new Object();
	$.ajax({
		url : presidentsUrl,
		type : 'GET',
		async: false,
		dataType : 'json',
		success : function(response) {
			if (0 == response.code) {
				presidents = response.data;
			}
			else {
				bootbox.alert({closeButton: false, size: "small", title: "提示", message: response.message + ' (' + response.code +')'});
			}
		},
		error : function(msg, url, line) {
			bootbox.alert({closeButton: false, size: "small", title: "错误", message: 'msg = ' + msg + ', url = ' + url + ', line = ' + line });
		}
	});
	return presidents;
};

function getDirectors(schoolId) {
	var directos = new Object();
	$.ajax({
		url : directorsUrl + "?SchoolId=" + schoolId,
		type : 'GET',
		async: false,
		dataType : 'json',
		success : function(response) {
			if (0 == response.code) {
				directos = response.data;
			}
			else {
				bootbox.alert({closeButton: false, size: "small", title: "提示", message: response.message + ' (' + response.code +')'});
			}
		},
		error : function(msg, url, line) {
			bootbox.alert({closeButton: false, size: "small", title: "错误", message: 'msg = ' + msg + ', url = ' + url + ', line = ' + line });
		}
	});
	return directos;
};

function getGroupLeaders(branchSchoolId) {
	var groupLeaders = new Object();
	$.ajax({
		url : groupLeadersUrl + "?BranchSchoolId=" + branchSchoolId,
		type : 'GET',
		async: false,
		dataType : 'json',
		success : function(response) {
			if (0 == response.code) {
				groupLeaders = response.data;
			}
			else {
				bootbox.alert({closeButton: false, size: "small", title: "提示", message: response.message + ' (' + response.code +')'});
			}
		},
		error : function(msg, url, line) {
			bootbox.alert({closeButton: false, size: "small", title: "错误", message: 'msg = ' + msg + ', url = ' + url + ', line = ' + line });
		}
	});
	return groupLeaders;
};

function getSchoolMap() {
	var schoolMap = new Object();
	$.ajax({
		url : schoolsUrl,
		type : 'GET',
		async: false,
		dataType : 'json',
		success : function(response) {
			if (0 == response.code) {
				response.data.forEach(element => { schoolMap[element.id] = element;});
			}
			else {
				bootbox.alert({closeButton: false, size: "small", title: "提示", message: response.message + ' (' + response.code +')'});
			}
		},
		error : function(msg, url, line) {
			bootbox.alert({closeButton: false, size: "small", title: "错误", message: 'msg = ' + msg + ', url = ' + url + ', line = ' + line });
		}
	});
	return schoolMap;
};

function initSchoolComboBox(schoolMap) {
	if (schoolMap != null) {
		var schoolCombobox = document.getElementById("school_combobox");
		for (var key in schoolMap) {
			var option = document.createElement("option");
			option.value = key;
			option.text = schoolMap[key].name;
			schoolCombobox.add(option, null);
		};
	}
};

function getBranchSchoolMap(schoolId) {
	var branchSchoolMap = new Object();
	if (schoolId != null && schoolId != "") {
		$.ajax({
			url : branchSchoolsUrl + "?SchoolId=" + schoolId,
			type : 'GET',
			async: false,
			dataType : 'json',
			success : function(response) {
				if (0 == response.code) {
					response.data.forEach(element => { branchSchoolMap[element.id] = element;});
				}
				else {
					bootbox.alert({closeButton: false, size: "small", title: "提示", message: response.message + ' (' + response.code +')'});
				}
			},
			error : function(msg, url, line) {
				bootbox.alert({closeButton: false, size: "small", title: "错误", message: 'msg = ' + msg + ', url = ' + url + ', line = ' + line });
			}
		});
	}
	return branchSchoolMap;
};

function initBranchSchoolComboBox(branchSchoolMap) {
	if (branchSchoolMap != null) {
		var branchSchoolCombobox = document.getElementById("branch_school_combobox");
		for (var key in branchSchoolMap) {
			var option = document.createElement("option");
			option.value = key;
			option.text = branchSchoolMap[key].name;
			branchSchoolCombobox.add(option, null);
		};
	}
};

function sleep(ms) {
	return new Promise(resolve => setTimeout(resolve, ms));
};

function convertDateTimeToString(datetime) {
	return datetime.getFullYear() + "-" + ("0"+(datetime.getMonth()+1)).slice(-2) + "-" + ("0" + datetime.getDate()).slice(-2) + " "
		   + ("0" + datetime.getHours()).slice(-2) + ":" + ("0" + datetime.getMinutes()).slice(-2) +  ":" + ("0" + datetime.getSeconds()).slice(-2) + "."
		   + ("0" + datetime.getMilliseconds()).slice(-3);
};