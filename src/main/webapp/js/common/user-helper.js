var UserHelper = function() {
	this.httpRequestHandler = new HttpRequestHandler();
};

UserHelper.prototype.getPresidents = function() {
	var presidents = new Object();
	var response = this.httpRequestHandler.syncGet(presidentsUrl);
	if (response.code == 0) {
		presidents = response.data;
	}
	else {
		console.log('Get presidents failed. Error message: ' + response.message + ' (' + response.code +')');
	}
	return presidents;
};