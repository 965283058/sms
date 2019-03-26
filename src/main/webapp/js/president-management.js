var presidentTable;

function statusFormatter(value) {
	if (value == true)
		return statusTypeDictionary[1];
	if (value == false)
		return statusTypeDictionary[0];
};

function roleFormatter(value) {
	return RoleTypeDictionary[value];
};

function isPresidentSelected() {
	if (!presidentTable.isItemSelected()) {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: '请先选择校长。'});
		return false;
	}
	return true;
};

function editPresident(operationType)
{
	switch (operationType) 
	{
	case OperationType.VIEW:
	case OperationType.UPDATE:
		if (isPresidentSelected()) {
			window.parent.$("#content_frame").attr('src', "jsp/userEdit.jsp?Action=" + operationType + "&UserId=" + presidentTable.getSelectedItemId());
		}
		break;
	case OperationType.ADD:
		window.parent.$("#content_frame").attr('src', "jsp/userEdit.jsp?Action=" + operationType + "&RoleType=" + RoleTypeEnum.PRESIDENT);
		break;
	case OperationType.DELETE:
		if (isPresidentSelected()) {
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
	presidentTable = new Table("president_table", PaginationQueryType.BY_ID, 760, 18, presidentsUrl, getTableColumns());
	presidentTable.create();
});
