var selectedSchoolId = 0;
var selectedBranchSchoolId = 0;
var groupLeaderTable;

function statusFormatter(value) {
	if (value == true)
		return statusTypeDictionary[1];
	if (value == false)
		return statusTypeDictionary[0];
};

function roleFormatter(value) {
	return RoleTypeDictionary[value];
};

function isGroupLeaderSelected() {
	if (!groupLeaderTable.isItemSelected()) {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: '请先选择班主任。'});
		return false;
	}
	return true;
};

function onSchoolChange(object) {
	selectedSchoolId = document.getElementById("school_combobox").value;
	
	// Clear branch school combobox
	$("#branch_school_combobox").empty();
	
	// Fill branch school combobox
	var branchSchoolMap = getBranchSchoolMap(selectedSchoolId);
	initBranchSchoolComboBox(branchSchoolMap);
	selectedBranchSchoolId = document.getElementById("branch_school_combobox").value;
	
	// Get group leaders of the selected branch school
	groupLeaderTable.resetQueryParams();
	var url = selectedBranchSchoolId == "" ? "#" : groupLeadersUrl + "?BranchSchoolId=" + selectedBranchSchoolId;
	groupLeaderTable.refresh(url);
};

function onBranchSchoolChange(object) {
	selectedSchoolId = document.getElementById("school_combobox").value;
	selectedBranchSchoolId = document.getElementById("branch_school_combobox").value;
	
	// Get group leaders for the selected branch school
	groupLeaderTable.resetQueryParams();
	var url = selectedBranchSchoolId == "" ? "#" : groupLeadersUrl + "?BranchSchoolId=" + selectedBranchSchoolId;
	groupLeaderTable.refresh(url);
};

function editGroupLeader(operationType)
{
	switch (operationType) 
	{
	case OperationType.VIEW:
	case OperationType.UPDATE:
		if (isGroupLeaderSelected()) {
			window.parent.$("#content_frame").attr('src', "jsp/userEdit.jsp?Action=" + operationType + "&UserId=" + groupLeaderTable.getSelectedItemId());
		}
		break;
	case OperationType.ADD:
		if (selectedBranchSchoolId != "") {
			window.parent.$("#content_frame").attr('src', "jsp/userEdit.jsp?Action=" + operationType + "&RoleType=" + RoleTypeEnum.GROUP_LEADER + "&BranchSchoolId=" + selectedBranchSchoolId);
		}
		else {
			bootbox.alert({closeButton: false, size: "small", title: "提示", message: '请先选择分园。'});
		}
		break;
	case OperationType.DELETE:
		if (isGroupLeaderSelected()) {
			// TBD
		}
		break;
	}
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
	
	// Initialize branch school name combo box for selected school
	var branchSchoolMap = getBranchSchoolMap(selectedSchoolId);
	initBranchSchoolComboBox(branchSchoolMap);
	selectedBranchSchoolId = document.getElementById("branch_school_combobox").value;

	var url = selectedBranchSchoolId == "" ? "#" : groupLeadersUrl + "?BranchSchoolId=" + selectedBranchSchoolId;
	groupLeaderTable = new Table("group_leader_table", PaginationQueryType.BY_ID, 760, 18, url, getTableColumns());
	groupLeaderTable.create();
});
