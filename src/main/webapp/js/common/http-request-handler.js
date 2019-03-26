// https://www.jianshu.com/p/fe0159f8beb4

var HttpRequestHandler = function() {
};

HttpRequestHandler.prototype.asyncGet = function(url) {
	return new Promise(function(resolve, reject) {
	    $.ajax({
	    	url : url,
	        type : "GET",
	        async : true,
	        dataType : 'json',
	        success: function(response) {
	        	console.log('GET: ' + url + '; Code:' + response.code + '; Message:' + response.message);
	        	resolve(response);
	        },
	        error: function(xmlHttpRequest, textStatus, errorThrown) {
	        	console.log('HTTP Error: ' + xmlHttpRequest.status);
	        	var response = new Object();
	        	response.code = xmlHttpRequest.status;
	        	response.message = textStatus;
	        	reject(response);
	        }
	    });
	});
};

HttpRequestHandler.prototype.syncGet = function(url) {
	var result = new Object();
	$.ajax({
		url : presidentsUrl,
		type : 'GET',
		async: false,
		dataType : 'json',
		success : function(response) {
			console.log('GET: ' + url + '; Code:' + response.code + '; Message:' + response.message);
			result = response;
		},
		error : function(msg, url, line) {
			console.log('HTTP Error: msg = ' + msg + ', url = ' + url + ', line = ' + line );
		}
	});
	return result;
};

HttpRequestHandler.prototype.asyncPost = function(url, jsonData) {
	return new Promise(function(resolve, reject) {
	    $.ajax({
	    	url : url,
			type : 'POST',
			dataType : 'json',
			contentType:"application/json; charset=utf-8",
			data : JSON.stringify(jsonData),
	        success: function(response) {
	        	console.log('POST: ' + url + '; Code:' + response.code + '; Message:' + response.message);
	        	resolve(response);
	        },
	        error: function(xmlHttpRequest, textStatus, errorThrown) {
	        	console.log('HTTP Error: ' + xmlHttpRequest.status);
	        	var response = new Object();
	        	response.code = xmlHttpRequest.status;
	        	response.message = textStatus;
	        	reject(response);
	        }
	    });
	});
};

HttpRequestHandler.prototype.asyncPut = function(url, jsonData) {
	return new Promise(function(resolve, reject) {
	    $.ajax({
	    	url : url,
			type : 'PUT',
			dataType : 'json',
			contentType:"application/json; charset=utf-8",
			data : JSON.stringify(jsonData),
	        success: function(response) {
	        	console.log('PUT: ' + url + '; Code:' + response.code + '; Message:' + response.message);
	        	resolve(response);
	        },
	        error: function(xmlHttpRequest, textStatus, errorThrown) {
	        	console.log('HTTP Error: ' + xmlHttpRequest.status);
	        	var response = new Object();
	        	response.code = xmlHttpRequest.status;
	        	response.message = textStatus;
	        	reject(response);
	        }
	    });
	});
};
