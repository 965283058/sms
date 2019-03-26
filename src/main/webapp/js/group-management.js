function editGroup(operationType)
{
	switch (operationType) 
	{
	case OperationType.VIEW:
	case OperationType.UPDATE:
		if (isGroupSelected()) {
			var url = "jsp/groupEdit.jsp?Action=" + operationType + "&SchoolId=" + selectedSchoolId + "&BranchSchoolId=" + selectedBranchSchoolId + "&GroupId=" + groupTable.getSelectedItemId();
			window.parent.$("#content_frame").attr('src', url);
		}
		break;
	case OperationType.ADD:
		var url = "jsp/groupEdit.jsp?Action=" + operationType + "&SchoolId=" + selectedSchoolId + "&BranchSchoolId=" + selectedBranchSchoolId;
		window.parent.$("#content_frame").attr('src', url);
		break;
	case OperationType.DELETE:
		if (isGroupSelected()) {
			// TBD
		}
		break;
	}
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
	
	// Create group table
	var url = selectedBranchSchoolId == "" ? "#" : groupsUrl + "?BranchSchoolId=" + selectedBranchSchoolId;
	groupTable = new Table("group_table", PaginationQueryType.BY_ID, 760, 18, url, getGroupTableColumns());
	groupTable.create();
});
