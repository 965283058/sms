$(document).ready(function() {
	clearLocalStorageData();
});

function validate()
{
  var username=$("#username_input").val();
  if(username=="")
  {
	  bootbox.alert({closeButton: false, size: "small", title: "提示", message: "用户名不能为空。"});
	  return false;
  }
  
  var password=$("#password_input").val();
  if(password=="")
  {
	  bootbox.alert({closeButton: false, size: "small", title: "提示", message: "密码不能为空。"});
	  return false;
  }
  
  if(username && password)
  {
	  return true;
  }
};

function logon()
{  
  if (validate())
  {
	  var username=$("#username_input").val();
	  var password=$("#password_input").val();
	  $.ajax
	  ({
	    url: logonUrl,
	    type: 'POST',
	    dataType: 'json',
	    data:
	    {
	    	user_name:username,
	    	password:password
	    },	    
	    success: function(response)
	    {
	    	if (0 == response.code)
	    	{
	    		localStorage.setItem("user_name", response.data.user.logName);
	    		localStorage.setItem("user_id", response.data.user.id);
	    		
	    		// Redirect to main page
	    		var relativeRedirectUrl = response.data.redirectUrl;
	    		var absoluteRedirectUrl = window.location.href; 
	    		if (absoluteRedirectUrl[absoluteRedirectUrl.length -1] == '/' && relativeRedirectUrl.length > 0 && relativeRedirectUrl[0] == '/')
    			{
	    			window.location.href = absoluteRedirectUrl.substr(0, absoluteRedirectUrl.length - 1) + relativeRedirectUrl;
    			}
	    		else if (absoluteRedirectUrl.endsWith("/jsp/login.jsp")) {
	    			window.location.href = absoluteRedirectUrl.substring(0, absoluteRedirectUrl.length - 14) + relativeRedirectUrl;
	    		}
	    	}
	    	else
	    	{
	    		bootbox.alert({closeButton: false, size: "small", title: "提示", message: response.message + ' (' + response.code +')'});
	    	}
	    },
	    error: function (msg, url, line) 
	    {
	    	bootbox.alert({closeButton: false, size: "small", title: "错误", message: 'msg = ' + msg + ', url = ' + url + ', line = ' + line });
	    }
	  });
  }
};