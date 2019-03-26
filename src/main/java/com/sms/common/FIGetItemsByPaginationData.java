package com.sms.common;

import com.sms.common.pagination.PaginationData;

import net.sf.json.JSONObject;

@FunctionalInterface
public interface FIGetItemsByPaginationData {
    public DataQueryResult<JSONObject>  getItemsByPaginationData(PaginationData paginationData);
}
