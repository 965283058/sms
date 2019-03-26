var monitorTable;
var singleFileUploader;
var monitorPhotoSelected = false;
var httpRequestHandler = new HttpRequestHandler();
var uploadedFilePath;

$(document).ready(function() {
	$('#install_datepicker').datetimepicker({format: 'Y-MM-DD', useCurrent: true});
	
	$('#monitor_photo_file').on('change',function(){
		var file = $(this)[0].files[0];
	    singleFileUploader = new SingleFileUploader(file, monitorPhotoUrl);
	    singleFileUploader.progressHandler = uploadProgressHandler;
	    monitorPhotoSelected = true;
	    $(this).next('.custom-file-label').html(file.name);
    });
	
	$('#monitor_model').on('hidden.bs.modal', function() {
		resetMonitorModal();
	});
	
	groupTable = new Table("group_table", PaginationQueryType.BY_ID, 370, 10, "#", getGroupTableColumns());
	groupTable.selectFirstRowAfterLoading = true;
	groupTable.onClickHandler = onGroupSelected;
	groupTable.create();
	
	// Create monitor table
	monitorTable = new Table("monitor_table", PaginationQueryType.BY_ID, 370, 5, "#", getMonitorTableColumns());
	monitorTable.create();
	
	// Load data
	httpRequestHandler.asyncGet(schoolsUrl).then(function(response) {
		if (0 == response.code) {
			var schoolMap = new Object();
			response.data.forEach(element => { schoolMap[element.id] = element;});
			
			// Initialize school name combo box
			initSchoolComboBox(schoolMap);
			selectedSchoolId = document.getElementById("school_combobox").value;
			
			httpRequestHandler.asyncGet(branchSchoolsUrl + "?SchoolId=" + selectedSchoolId).then(function(response) {
				if (0 == response.code) {
					var branchSchoolMap = new Object();
					response.data.forEach(element => { branchSchoolMap[element.id] = element;});
					
					initBranchSchoolComboBox(branchSchoolMap);
					selectedBranchSchoolId = document.getElementById("branch_school_combobox").value;
					
					// Update group table
					var groupUrl = selectedBranchSchoolId == "" ? "#" : groupsUrl + "?BranchSchoolId=" + selectedBranchSchoolId;
					groupTable.refresh(groupUrl);
				}
			});
		}
	});
});

function onSchoolChangeEx(object) {
	onSchoolChange(object);
	monitorTable.clear();
};

function onBranchSchoolChangeEx(object) {
	onBranchSchoolChange(object);
	monitorTable.clear();
};

function uploadProgressHandler(event) {
    var percent = 0;
    var position = event.loaded || event.position;
    var total = event.total;
    var progressBarId = "#progress-wrp";
    if (event.lengthComputable) {
        percent = Math.ceil(position / total * 100);
    }
    $(progressBarId + " .progress-bar").css("width", + percent + "%");
    $(progressBarId + " .status").text(percent + "%");
};

function resetProgressBar() {
	document.getElementById("progress-wrp").style.display = 'none';
	var percent = 0;
	var progressBarId = "#progress-wrp";
    $(progressBarId + " .progress-bar").css("width", + percent + "%");
    $(progressBarId + " .status").text(percent + "%");
};

function resetMonitorModal() {
	$('#monitor_model').find('form').trigger('reset');
	document.getElementById("monitor_name_input").disabled = false;
	document.getElementById("monitor_group_name_input").disabled = true;
	document.getElementById("monitor_branch_school_name_input").disabled = true;
	document.getElementById("monitor_group_name_input").value = groupTable.getSelectedItem().name;
	document.getElementById("monitor_branch_school_name_input").value =	$('#branch_school_combobox :selected').text();
	document.getElementById("camera_address_input").disabled = false;
	document.getElementById("monitor_description_input").disabled = false;
	document.getElementById("monitor_photo_file").disabled = false;
	document.getElementById("monitor_photo_file").value = null;
	$('#monitor_photo_file').next('.custom-file-label').html("选择封面图片");
	document.getElementsByClassName("datetimepicker-input")[0].disabled = false;
	document.getElementById("ok_btn").onclick = null;
	resetProgressBar();
	monitorPhotoSelected = false;
};

function onGroupSelected() {
	var selectedGroupId = groupTable.getSelectedItemId();
	if (selectedGroupId != null) {
		var url = monitorsUrl + "?GroupId=" + selectedGroupId;
		monitorTable.refresh(url);
	}
};

function isGroupSelected() {
	if (!groupTable.isItemSelected()) {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: '请先选择班级。'});
		return false;
	}
	return true;
};

function isMonitorSelected() {
	if (!monitorTable.isItemSelected()) {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: '请先选择监控。'});
		return false;
	}
	return true;
};

function setMonitorInstallDate(startDate) {
	if (startDate != null) {
		$('#install_datepicker').data('datetimepicker').date(new Date(startDate));
	}
	else {
		$('#install_datepicker').data('datetimepicker').clear();
	}
};

function showEmptyMonitorModel() {
	resetMonitorModal();
	$("#monitor_model").modal();
	document.getElementById("ok_btn").onclick = function() { uploadPhotoAndCreateMonitor(); };
	resetProgressBar();
};

function showMonitorModal(monitor, operationType) {
	resetMonitorModal();
	$("#monitor_model").modal();
	monitorPhotoSelected = false;
	document.getElementById("monitor_name_input").value = monitor.name;
	document.getElementById("camera_address_input").value = monitor.camera_address;
	document.getElementById("monitor_description_input").value = monitor.description != null ? monitor.description : "";
	if (monitor.photo_url != null) {
		uploadedFilePath = monitor.photo_url;
		var imageFileName = uploadedFilePath.substring(uploadedFilePath.lastIndexOf("\\") + 1);
		$("#monitor_photo_file").attr("value", imageFileName);
		$('#monitor_photo_file').next('.custom-file-label').html(imageFileName);
	}
	setMonitorInstallDate(monitor.install_date);

	if (operationType == OperationType.VIEW) {
		document.getElementById("monitor_name_input").disabled = true;
		document.getElementById("camera_address_input").disabled = true;
		document.getElementById("monitor_description_input").disabled = true;
		document.getElementById("monitor_photo_file").disabled = true;
		document.getElementsByClassName("datetimepicker-input")[0].disabled = true;
		document.getElementById("ok_btn").onclick = function() {
			$('#monitor_model').modal('hide'); 
		};
	}
	else {
		document.getElementById("ok_btn").onclick = function() {
			uploadPhotoAndUpdateMonitor(monitorTable.getSelectedItemId());
		};
	}
};

function createMonitor() {
	httpRequestHandler.asyncPost(monitorUrl, getMonitorJsonData()).then(function(response) {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: response.message + ' (' + response.code +')'});
		if (response.code == 0) {
			$('#monitor_model').modal('hide');
			onGroupSelected();
		}
	}, function(error) {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: "新建监控失败。具体原因：" + error.message + ' (' + error.code +')'});
	});
};

async function uploadPhotoAndCreateMonitor() {
	if (!validate())
		return;
	
	if (monitorPhotoSelected) {
		// Upload file
		document.getElementById("progress-wrp").style.display = 'block';
		resetProgressBar();
		await singleFileUploader.upload().then(function(response) {
			if (0 == response.code) {
				uploadedFilePath = response.data;
				createMonitor();
			}
			else {
				bootbox.alert({closeButton: false, size: "small", title: "提示", message:  "图片上传失败。具体原因：" + response.message + ' (' + response.code +')'});
				$('#monitor_model').modal('hide');
			}
		}, function(error) {
			bootbox.alert({closeButton: false, size: "small", title: "提示", message: "图片上传失败。具体原因：" + error.message + ' (' + error.code +')'});
		});
	}
	else {
		createMonitor();
	}
};

function updateMonitor(monitorId) {
	httpRequestHandler.asyncPut(monitorUrl + "/" + monitorId, getMonitorJsonData()).then(function(response) {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: response.message + ' (' + response.code +')'});
		if (response.code == 0) {
			$('#monitor_model').modal('hide');
			onGroupSelected();
		}
	}, function(error) {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: "修改监控失败。具体原因：" + error.message + ' (' + error.code +')'});
	});
};

async function uploadPhotoAndUpdateMonitor(monitorId) {
	if (!validate())
		return;
	
	if (monitorPhotoSelected) {
		// Upload file
		document.getElementById("progress-wrp").style.display = 'block';
		resetProgressBar();
		await singleFileUploader.upload().then(function(response) {
			if (0 == response.code) {
				uploadedFilePath = response.data;
				updateMonitor(monitorId);
			}
			else {
				bootbox.alert({closeButton: false, size: "small", title: "提示", message: "图片上传失败。具体原因：" + response.message + ' (' + response.code +')'});
				$('#monitor_model').modal('hide');
			}
		}, function(error) {
			bootbox.alert({closeButton: false, size: "small", title: "提示", message: "图片上传失败。具体原因：" + error.message + ' (' + error.code +')'});
		});
	}
	else {
		updateMonitor(monitorId);
	}
};

function validate() {
	var monitorName = $("#monitor_name_input").val().trim();
	if (monitorName == "") {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: "监控名称不能为空。"});
		return false;
	}
	var cameraAddress = $("#camera_address_input").val().trim();
	if (cameraAddress == "") {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: "视频地址不能为空。"});
		return false;
	}
	return true;
}

function getMonitorJsonData() {
	var monitorData = {};
	monitorData["name"] = $("#monitor_name_input").val().trim();
	monitorData["group_id"] = groupTable.getSelectedItemId();
	monitorData["camera_address"] = $("#camera_address_input").val().trim();
	monitorData["install_date"] = getMonitorInstallDateString();
	monitorData["description"] = $("#monitor_description_input").val().trim() != "" ? $("#monitor_description_input").val().trim() : null;
	
	var photoFileName = $("#monitor_photo_file").next('.custom-file-label')[0].textContent;
	if (photoFileName == "选择封面图片") {
		monitorData["photo_url"] = null;
	}
	else {
		monitorData["photo_url"] = uploadedFilePath;
	}
	
	monitorPhotoSelected
	return monitorData;
};

function getMonitorInstallDateString() {
	var date = $('#install_datepicker').data('datetimepicker').date();
	return moment(date).format('Y-MM-DD');
};

function editMonitor(operationType)
{
	switch (operationType) 
	{
	case OperationType.VIEW:
	case OperationType.UPDATE:
		if (isMonitorSelected()) {
			var url = monitorUrl + "/" + monitorTable.getSelectedItemId();
			httpRequestHandler.asyncGet(url).then(function(response) {
				if (0 == response.code) {
					showMonitorModal(response.data, operationType);
				}
			});
		}
		break;
	case OperationType.ADD:
		if (isGroupSelected()) {
			showEmptyMonitorModel();
		}
		break;
	case OperationType.DELETE:
		if (isMonitorSelected()) {
			// TBD
		}
		break;
	}
};

function getMonitorTableColumns() {
	var columns = [
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
			title : '监控名称',
			field : 'name'
		},
		{
			title : '安装时间',
			field : 'install_date'
		},
		{
			title : '监控地址',
			field : 'camera_address'
		}
	];
	return columns;
};
