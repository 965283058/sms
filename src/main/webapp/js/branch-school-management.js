var selectedSchoolId = 0;
var branchSchoolTable;

function isBranchSchoolSelected() {
	if (!branchSchoolTable.isItemSelected()) {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: '请先选择分园。'});
		return false;
	}
	return true;
};

function editBranchSchool(operationType)
{
	switch (operationType) 
	{
	case OperationType.VIEW:
	case OperationType.UPDATE:
		if (isBranchSchoolSelected()) {
			var selectedBranchSchoolId = branchSchoolTable.getSelectedItemId();
			window.parent.$("#content_frame").attr('src', "jsp/branchSchoolEdit.jsp?Action=" + operationType + "&SchoolId=" + selectedSchoolId + "&BranchSchoolId=" + selectedBranchSchoolId);
		}
		break;
	case OperationType.ADD:
		if (selectedSchoolId != "") {
			window.parent.$("#content_frame").attr('src', "jsp/branchSchoolEdit.jsp?Action=" + operationType + "&SchoolId=" + selectedSchoolId);
		}
		else {
			bootbox.alert({closeButton: false, size: "small", title: "提示", message: '请先选择幼儿园。'});
		}
		break;
	case OperationType.DELETE:
		if (isBranchSchoolSelected()) {
			// TBD
		}
		break;
	}
};

function onSchoolChange(object) {
	branchSchoolTable.resetQueryParams();
	selectedSchoolId = document.getElementById("school_combobox").value;
	branchSchoolTable.refresh(branchSchoolsUrl + "?SchoolId=" + selectedSchoolId);
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
			title : '分园名称',
			field : 'name'
		},
		{
			title : '联系电话',
			field : 'telephone_number'
		},
		{
			title : '园长',
			field : 'director_name'
		},
		{
			title : '地址',
			field : 'address'
		},
		{
			title : '班级数量',
			field : 'group_count'
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
	
	var url = selectedSchoolId == "" ? "#" : branchSchoolsUrl + "?SchoolId=" + selectedSchoolId;
	branchSchoolTable = new Table("branch_school_table", PaginationQueryType.BY_ID, 760, 18, url, getTableColumns());
	branchSchoolTable.create();
});
