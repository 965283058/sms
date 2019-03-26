$(function () 
{
	$('#sidebar_menu').metisMenu();
	
	$('#modify_password_btn').click(function(e) {
		$("#change_password_model").modal();
	});
	
	$('#logout_btn').click(function(e) {
		bootbox.confirm({
			title: "退出系统",
			message: "您确定要退出系统吗？",
			buttons: {
				cancel: { label: '<i class="fa fa-times"></i> 取消' },
				confirm: { label: '<i class="fa fa-check"></i> 确定' }
			},
			callback: function (result) {
				if (result == true) {
					var userId = localStorage.getItem("user_id");
					logout(userId);
				}
			}
		});
	});
});

function logout(userId) {
	$.ajax
	({
		url: logoutUrl,
		type: 'POST',
		dataType: 'json',
		data:
		{
			user_id:userId
		},
		success: function(response)
		{
			if (0 == response.code)
			{
	    		// Redirect to logon page
				window.location.href = basePath;
				clearLocalStorageData();
			}
		},
		error: function (msg, url, line)
		{
			bootbox.alert({closeButton: false, size: "small", title: "错误", message: 'msg = ' + msg + ', url = ' + url + ', line = ' + line });
		}
	});
};

function validatePasswords() {
	var oldPassword = document.getElementById("old_password_input").value.trim();
	if (oldPassword == "") {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: "旧密码不能为空。"});
		return false;
	}
	
	var newPassword = document.getElementById("new_password_input").value.trim();
	if (newPassword == "") {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: "新密码不能为空。"});
		return false;
	}
	
	var confirmedNewPassword = document.getElementById("confirmed_new_password_input").value.trim();
	if (confirmedNewPassword == "") {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: "二次输入的新密码不能为空。"});
		return false;
	}
	
	if (oldPassword == newPassword) {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: "旧密码和新密码必须不同。"});
		return false;
	}
	
	if (newPassword != confirmedNewPassword) {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: "两次输入的新密码不相同。"});
		return false;
	}

	return true;
};

function getPasswordsJsonData() {
	var passwords = {};
	passwords["old_password"] = document.getElementById("old_password_input").value.trim();
	passwords["new_password"] = document.getElementById("new_password_input").value.trim();
	return passwords;
}

function changePassword() {
	var userId = localStorage.getItem("user_id");
	if (validatePasswords()) {
		$.ajax({
			url : userUrl + "/" + userId + "/Password",
			type : 'PUT',
			dataType : 'json',
			contentType:"application/json; charset=utf-8",
			data : JSON.stringify(getPasswordsJsonData()),
			success : function(response) {
				bootbox.alert({closeButton: false, size: "small", title: "提示", message: response.message + ' (' + response.code +')'});
				if (0 == response.code) {
					$('#change_password_model').modal('hide');
				}
			},
			error : function(msg, url, line) {
				bootbox.alert({closeButton: false, size: "small", title: "错误", message: 'msg = ' + msg + ', url = ' + url + ', line = ' + line });
			}
		});
	}
};

$(document).ready(function() {
    $("a.load-page").on("click", function(e) 
    {
    	//this cancel the original event: the browser stops here
        e.preventDefault();
        
        //get the data-url attribute from the anchor
        var url = $(this).data('url');
        
        // Todo: add logon session id to url
        
        // Set src attribute in iframe to lead new page
        $('#content_frame').attr('src', url);
    });
});