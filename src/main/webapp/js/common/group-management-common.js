var selectedSchoolId = 0;
var selectedBranchSchoolId = 0;
var groupTable;

function isGroupSelected() {
	if (!groupTable.isItemSelected()) {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: '请先选择班级。'});
		return false;
	}
	return true;
};

function onSchoolChange(object) {
	// Clear branch school combobox
	$("#branch_school_combobox").empty();
	
	// Get branch schools of selected school
	var getBranchSchoolsUrl = branchSchoolsUrl + "?SchoolId=" + document.getElementById("school_combobox").value;
	httpRequestHandler.asyncGet(getBranchSchoolsUrl).then(function(response) {
		if (response.code == 0) {
			// Fill branch school combobox
			var branchSchoolMap = new Object();
			response.data.forEach(element => { branchSchoolMap[element.id] = element;});
			initBranchSchoolComboBox(branchSchoolMap);
			selectedBranchSchoolId = document.getElementById("branch_school_combobox").value;
			
			// Get groups for the selected branch school
			groupTable.resetQueryParams();
			var url = selectedBranchSchoolId == "" ? "#" : groupsUrl + "?BranchSchoolId=" + selectedBranchSchoolId;
			groupTable.refresh(url);
		}
	});
};

function onBranchSchoolChange(object) {
	selectedSchoolId = document.getElementById("school_combobox").value;
	selectedBranchSchoolId = document.getElementById("branch_school_combobox").value;
	
	// Get groups for the selected branch school
	groupTable.resetQueryParams();
	var url = selectedBranchSchoolId == "" ? "#" : groupsUrl + "?BranchSchoolId=" + selectedBranchSchoolId;
	groupTable.refresh(url);
};

function getGroupTableColumns() {
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
			title : '班级名称',
			field : 'name'
		},
		{
			title : '班主任',
			field : 'group_leader_name'
		},
		{
			title : '班主任电话',
			field : 'group_leader_telephone_number'
		},
		{
			title : '开班日期',
			field : 'start_date',
			//formatter : schoolTypeFormatter
		},
		{
			title : '学生数量',
			field : 'member_count'
		}
	];
	return columns;
};
