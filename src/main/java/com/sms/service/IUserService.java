package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.vo.UserVO;

public interface IUserService {
    
    public CommandResult getUser(Integer id);
    
    public CommandResult updateUser(Integer id, UserVO userVO);
    
    public CommandResult updatePassword(Integer id, String oldPassword, String newPassword);
    
    /*
    public DataQueryResult<JSONObject> getUsersByPaginationData(PaginationData paginationData);

    public CommandResult getUserNamesByRoleType(Integer roleType);

    public CommandResult createUser(UserViewObject userViewObject);
    
    */
}
