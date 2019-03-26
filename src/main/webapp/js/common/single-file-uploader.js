var SingleFileUploader = function(file, uploadUrl) {
    this.file = file;
    this.uploadUrl = uploadUrl;
    this.progressHandler = null;
};

SingleFileUploader.prototype.getFileType = function() {
    return this.file.type;
};

SingleFileUploader.prototype.getFileSize = function() {
    return this.file.size;
};

SingleFileUploader.prototype.getFileName = function() {
    return this.file.name;
};

SingleFileUploader.prototype.upload = function() {
	var instance = this;
	instance.uploadCompleted = false;
    instance.error = null;
    
    var formData = new FormData();
    formData.append("file", instance.file, instance.getFileName());
    formData.append("upload_file", true);
	
	return new Promise(function(resolve, reject) {
	    $.ajax({
	        type: "POST",
	        url: instance.uploadUrl,
	        async: true,
	        data: formData,
	        enctype: 'multipart/form-data',
	        cache: false,
	        contentType: false,
	        processData: false,
	        timeout: 600000, // ms
	        xhr: function() {
	        	if (instance.progressHandler != null) {
	        		var xhr = new window.XMLHttpRequest();
	                if (xhr.upload) {
	                	xhr.upload.addEventListener('progress', instance.progressHandler, false);
	                }
	                return xhr;
	        	}
	        },
	        success: function(response) {
	        	console.log('POST FILE: ' + instance.uploadUrl + '; File: ' + instance.getFileName() + '; Code:' + response.code + '; Message:' + response.message);
	        	resolve(response);
	        },
	        error: function(xmlHttpRequest, textStatus, errorThrown) {
	        	console.log('HTTP Error: ' + xmlHttpRequest.status);
	        	var response = new Object();
	        	response.code = xmlHttpRequest.status;
	        	response.message = textStatus;
	        	reject(response);
	        },
	    });
	});
};

/*SingleFileUploader.prototype.upload = function() {
    var instance = this;
    instance.uploadCompleted = false;
    instance.error = null;
    var result = new Object();
    
    var formData = new FormData();
    formData.append("file", instance.file, instance.getFileName());
    formData.append("upload_file", true);

    $.ajax({
        type: "POST",
        url: instance.uploadUrl,
        async: true,
        data: formData,
        enctype: 'multipart/form-data',
        cache: false,
        contentType: false,
        processData: false,
        timeout: 600000,
        xhr: function() {
        	if (instance.progressHandler != null) {
        		var xhr = new window.XMLHttpRequest();
                if (xhr.upload) {
                	xhr.upload.addEventListener('progress', instance.progressHandler, false);
                }
                return xhr;
        	}
        },
        success: function(response) {
        	if (instance.uploadCallback != null) {
        		result = response;
        		instance.uploadCallback(result);
        	}
        },
        error: function(error) {
        	if (instance.uploadCallback != null) {
        		result.code = -1;
        		result.message = "文件上传失败"
        		instance.uploadCallback(result);
        	}
        }
    });
};*/
