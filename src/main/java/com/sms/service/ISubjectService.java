package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.vo.SubjectVO;
import net.sf.json.JSONObject;

public interface ISubjectService {
    DataQueryResult<JSONObject> getSubjectsByPaginationData(User loggedInUser, Integer schoolId, PaginationData paginationData);
    CommandResult createSubject(SubjectVO subjectVO);
    CommandResult getSubject(Integer id);
    CommandResult updateSubject(Integer id, SubjectVO subjectVO);
    CommandResult deleteSubject(Integer id);
}
