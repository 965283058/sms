var $table;

function statusFormatter(value) {
	if (value == true)
		return statusTypeDictionary[1];
	if (value == false)
		return statusTypeDictionary[0];
};

function roleFormatter(value) {
	return RoleTypeDictionary[value];
};

function isUserSelected() {
	if (selectItemId == null) {
		bootbox.alert({closeButton: false, size: "small", title: "提示", message: '请先选择用户。'});
		return false;
	}
	return true;
};

function editUser(operationType)
{
	switch (operationType) 
	{
	case OperationType.VIEW:
	case OperationType.UPDATE:
		if (isUserSelected()) {
			window.parent.$("#content_frame").attr('src', "jsp/userEdit.jsp?Action=" + operationType + "&UserId=" + selectItemId);
		}
		break;
	case OperationType.ADD:
		window.parent.$("#content_frame").attr('src', "jsp/userEdit.jsp?Action=" + operationType);
		break;
	case OperationType.DELETE:
		if (isUserSelected()) {
			// TBD
		}
		break;
	}
};

$(document).ready(function() {
	// Reset Ids
	initTableQueryParameters();

	// Initialize table
	$('#table').bootstrapTable({
		height : '80%',
		url : usersUrl + "?RoleType=" + RoleTypeEnum.PRESIDENT,
		method : 'get', //请求方式（*）
		dataType : 'json',
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortable : false, //是否启用排序
		sortOrder : "asc", //排序方式		
		sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, //初始化加载第一页，默认第一页
		pageSize : countPerPage, //每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
		strictSearch : true,
		clickToSelect : true, //是否启用点击选中行
		height : 250, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "id", //每一行的唯一标识，一般为主键列
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		paginationLoop : false,
		paginationPreText : "上一页",
		paginationNextText : "下一页",
		queryParams : queryParams, //传递参数（*）
		responseHandler : responseHandler, //接受后台传过来的值进行绑定处理的函数
		onClickRow : onClickRow,
		onCheck : onCheck,
		columns : [
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
				title : '职位',
				field : 'role_id',
				formatter : roleFormatter
			},
			{
				title : '幼儿园',
				field : 'school_id'
			},
			{
				title : '分院',
				field : 'branch_school_id'
			},
			{
				title : '班级',
				field : 'group_id'
			},
			{
				title : '状态',
				field : 'status',
				formatter : statusFormatter 
			}
		]
	});
	$(".fixed-table-container").css({
		height : '80%'
	});
	$('#table').bootstrapTable('resetView', {
		height : '80%'
	});
});
