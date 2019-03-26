const PaginationQueryType = {
	BY_ID : 1,
	BY_CREATE_TIME : 2,
};

const PaginationPageMode = {
	NEXT_PAGE : 1,
	PREVIOUS_PAGE : 2,
};

function Table(tableId, queryType, tableHeight, maxRowPerPage, initUrl, tableHeader) {
	this.tableId = tableId;
	this.tableHeight = tableHeight;
	this.maxRowPerPage = maxRowPerPage;
	this.initUrl = initUrl;
	this.tableHeader = tableHeader;
	this.minDataId = 0;
	this.maxDataId = 0;
	this.currentOffsetId = 0;
	this.nextOffsetId = 0;
	this.selectedItemId = null;
	this.onClickHandler = null;
	this.queryType = queryType;
	this.minDataTime = null;
	this.maxDataTime = null;
	this.selectFirstRowAfterLoading = false;
};

Table.prototype.resetQueryParams = function() {
	this.minDataId = 0;
	this.maxDataId = 0;
	this.currentOffsetId = 0;
	this.nextOffsetId = 0;
	this.selectedItemId = null;
	this.minDataTime = null;
	this.maxDataTime = null;
};

Table.prototype.create = function() {
	$("#" + this.tableId).bootstrapTable({
		url : this.initUrl, 
		method : 'get', //请求方式（*）
		dataType : 'json',
		striped : false, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortable : false, //是否启用排序
		sortOrder : "asc", //排序方式		
		sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, //初始化加载第一页，默认第一页
		pageSize : this.maxRowPerPage, //每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
		strictSearch : true,
		clickToSelect : true, //是否启用点击选中行
		height : this.tableHeight, //如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "id", //每一行的唯一标识，一般为主键列
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		paginationLoop : false,
		paginationPreText : "上一页",
		paginationNextText : "下一页",
		queryParams : this.setQueryParams.bind(this), //传递参数（*）
		responseHandler : this.responseHandler.bind(this), //接受后台传过来的值进行绑定处理的函数
		onCheck : this.onCheck.bind(this),
		onLoadSuccess : this.onLoadSuccess.bind(this),
		columns : this.tableHeader
	});
};

Table.prototype.refresh = function(url) {
	this.resetQueryParams();
	$("#" + this.tableId).bootstrapTable('refreshOptions', { silent: true, url: url });
}

Table.prototype.clear = function() {
	this.resetQueryParams();
	$("#" + this.tableId).bootstrapTable('removeAll');
	document.getElementById(this.tableId).parentElement.parentElement.style.height = this.tableHeight;
	$("#" + this.tableId).bootstrapTable('resetView', {
		height : this.tableHeight
	});
};

Table.prototype.setQueryParams = function(params) {
	// Store new offset
	this.nextOffsetId = params.offset;

	// Create pagination data
	var paginationData = {};
	
	if (this.queryType == PaginationQueryType.BY_ID) {
		paginationData["query_type"] = PaginationQueryType.BY_ID;
		if (this.currentOffsetId > this.nextOffsetId) {
			paginationData["query_id"] = this.minDataId;
			paginationData["page_mode"] = PaginationPageMode.PREVIOUS_PAGE;
		}
		else {
			paginationData["query_id"] = this.maxDataId;
			paginationData["page_mode"] = PaginationPageMode.NEXT_PAGE;
		}
	}
	else if (this.queryType == PaginationQueryType.BY_CREATE_TIME) {
		paginationData["query_type"] = PaginationQueryType.BY_CREATE_TIME;
		if (this.currentOffsetId > this.nextOffsetId) {
			paginationData["query_create_time"] = convertDateTimeToString(new Date(this.maxDataTime));
			paginationData["page_mode"] = PaginationPageMode.PREVIOUS_PAGE;
		}
		else {
			if (this.maxDataTime == null) {
				paginationData["query_create_time"] = convertDateTimeToString(new Date());
			}
			else {
				paginationData["query_create_time"] = convertDateTimeToString(new Date(this.minDataTime));
			}
			paginationData["page_mode"] = PaginationPageMode.NEXT_PAGE;
		}
	}
	
	return {
		limit : this.maxRowPerPage, //显示一页多少记录
		offset : params.offset, //第几条记录
		paginationData : JSON.stringify(paginationData),
	};
};

Table.prototype.responseHandler = function(response) {
	if (response.dataset == null || response.dataset.length == 0) {
		return {
			'total' : 0,
			"rows" : {},
		};
	}
	
	this.currentOffsetId = this.nextOffsetId;
	
	if (this.queryType == PaginationQueryType.BY_ID) {
		this.minDataId = response.dataset[0].id;
		this.maxDataId = response.dataset[response.dataset.length - 1].id;
	}
	else if (this.queryType == PaginationQueryType.BY_CREATE_TIME) {
		this.maxDataTime = response.dataset[0].created_time;
		this.minDataTime = response.dataset[response.dataset.length - 1].created_time;
	}

	return {
		'total' : response.totalCount,
		"rows" : response.dataset,
	};
};

Table.prototype.onCheck = function(row, $element) {
	this.updateSelectedItemId(row.id);
	if (this.onClickHandler != null) {
		this.onClickHandler();
	}
};

Table.prototype.onLoadSuccess = function(data) {
	if (this.selectFirstRowAfterLoading == true && data && data.total > 0) {
		$("#" + this.tableId).bootstrapTable("check", 0);
	}
};

Table.prototype.updateSelectedItemId = function(id) {
	if (this.selectedItemId != id) {
		this.selectedItemId = id;
	}
};

Table.prototype.isItemSelected = function() {
	return this.selectedItemId != null ? true : false;
};

Table.prototype.getSelectedItemId = function() {
	return this.selectedItemId;
};

Table.prototype.getSelectedItem = function() {
	if (this.isItemSelected()) {
		return $("#" + this.tableId).bootstrapTable('getRowByUniqueId', this.selectedItemId);
	}
	return null;
};
