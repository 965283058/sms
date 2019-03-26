const PageQueryType = {
	NEXT_PAGE : 1,
	PREVIOUS_PAGE : 2,
};

var countPerPage = 18;
var minDataId = 0;
var maxDataId = 0;
var currentOffset = 0;
var nextOffset = 0;
var selectItemId = null;

function initTableQueryParameters() {
	minDataId = 0;
	maxDataId = 0;
	currentOffset = 0;
	nextOffset = 0;
	selectItemId = null;
}

function queryParams(params) {
	// Store new offset
	nextOffset = params.offset;

	// Create pagination data
	var paginationData = new Object();
	if (currentOffset > nextOffset) {
		paginationData.paginationQueryId = minDataId;
		paginationData.paginationQueryType = PageQueryType.PREVIOUS_PAGE;
	}
	else {
		paginationData.paginationQueryId = maxDataId;
		paginationData.paginationQueryType = PageQueryType.NEXT_PAGE;
	}
	
	return {
		limit : countPerPage, //显示一页多少记录
		offset : params.offset, //第几条记录
		paginationData : JSON.stringify(paginationData),
	};
};

function responseHandler(response) {
	if (response.dataset == null || response.dataset.length == 0) {
		return {
			'total' : 0,
			"rows" : {},
		};
	}
	
	currentOffset = nextOffset;
	minDataId = response.dataset[0].id;
	maxDataId = response.dataset[response.dataset.length - 1].id;

	return {
		'total' : response.totalCount,
		"rows" : response.dataset,
	};
};

function onClickRow(row, $element, field) {
	updateSelectedItemId(row.id);
};

function onCheck(row, $element) {
	updateSelectedItemId(row.id);
};

function updateSelectedItemId(id) {
	if (selectItemId != id) {
		selectItemId = id;
	}
};
