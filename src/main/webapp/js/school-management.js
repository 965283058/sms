var schoolTable;

function schoolTypeFormatter(value) {
	if (value == schoolTypeDictionary.PUBLIC)
		return "是";
	if (value == schoolTypeDictionary.PRIVATE)
		return "否";
};

function isSchoolSelected() {
	if (!schoolTable.isItemSelected()) {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: '请先选择幼儿园。'});
		return false;
	}
	return true;
};

function editSchool(operationType)
{
	switch (operationType) 
	{
	case OperationType.VIEW:
	case OperationType.UPDATE:
		if (isSchoolSelected()) {
			window.parent.$("#content_frame").attr('src', "jsp/schoolEdit.jsp?Action=" + operationType + "&SchoolId=" + schoolTable.getSelectedItemId());
		}
		break;
	case OperationType.ADD:
		window.parent.$("#content_frame").attr('src', "jsp/schoolEdit.jsp?Action=" + operationType);
		break;
	case OperationType.DELETE:
		if (isSchoolSelected()) {
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
			title : '幼儿园名称',
			field : 'name'
		},
		{
			title : '联系电话',
			field : 'telephone_number'
		},
		{
			title : '校长',
			field : 'president_name'
		},
		{
			title : '是否公立',
			field : 'school_type',
			formatter : schoolTypeFormatter
		},
		{
			title : '分园数量',
			field : 'branch_school_count'
		}
	];
	
	return columns;
}

$(document).ready(function() {
	schoolTable = new Table("school_table", PaginationQueryType.BY_ID, 760, 18, schoolsUrl, getTableColumns());
	schoolTable.create();
});
