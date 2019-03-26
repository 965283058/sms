var SchoolHelper = function() {
	this.httpRequestHandler = new HttpRequestHandler();
};

SchoolHelper.prototype.getSchools = function() {
	var schools = new Object();
	var response = this.httpRequestHandler.asyncGet(schoolsUrl);
	if (response.code == 0) {
		schools = response.data;
	}
	else {
		console.log('Get presidents failed. Error message: ' + response.message + ' (' + response.code +')');
	}
	return schools;
};