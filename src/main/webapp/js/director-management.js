var selectedSchoolId = 0;
var directorTable;

function statusFormatter(value) {
	if (value == true)
		return statusTypeDictionary[1];
	if (value == false)
		return statusTypeDictionary[0];
};

function roleFormatter(value) {
	return RoleTypeDictionary[value];
};

function isDirectorSelected() {
	if (!directorTable.isItemSelected()) {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: '请先选择园长。'});
		return false;
	}
	return true;
};

function editDirector(operationType)
{
	switch (operationType) 
	{
	case OperationType.VIEW:
	case OperationType.UPDATE:
		if (isDirectorSelected()) {
			window.parent.$("#content_frame").attr('src', "jsp/userEdit.jsp?Action=" + operationType + "&UserId=" + directorTable.getSelectedItemId());
		}
		break;
	case OperationType.ADD:
		if (selectedSchoolId != "") {
			window.parent.$("#content_frame").attr('src', "jsp/userEdit.jsp?Action=" + operationType + "&RoleType=" + RoleTypeEnum.DIRECTOR + "&SchoolId=" + selectedSchoolId);
		}
		else {
			bootbox.alert({closeButton: false, size: "small", title: "提示", message: '请先选择幼儿园。'});
		}
		break;
	case OperationType.DELETE:
		if (isDirectorSelected()) {
			// TBD
		}
		break;
	}
};

function onSchoolChange(object) {
	directorTable.resetQueryParams();
	selectedSchoolId = document.getElementById("school_combobox").value;
	directorTable.refresh(directorsUrl + "?SchoolId=" + selectedSchoolId);
};

function getTableColumns() {
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
			title : '用户名',
			field : 'log_name'
		},
		{
			title : '姓名',
			field : 'name'
		},
		{
			title : '电话号码',
			field : 'telephone_number'
		},
		{
			title : '状态',
			field : 'status',
			formatter : statusFormatter 
		}
	];
	return columns;
};

$(document).ready(function() {
	// Get school list
	var schoolMap = getSchoolMap();
	
	// Initialize school name combo box
	initSchoolComboBox(schoolMap);
	selectedSchoolId = document.getElementById("school_combobox").value;

	var url = selectedSchoolId == "" ? "#" : directorsUrl + "?SchoolId=" + selectedSchoolId;
	directorTable = new Table("director_table", PaginationQueryType.BY_ID, 760, 18, url, getTableColumns());
	directorTable.create();
});
