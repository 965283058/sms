package com.sms.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.sms.model.File;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMapper {
    int insert(@Param("pojo") File pojo);

    int insertSelective(@Param("pojo") File pojo);

    int insertList(@Param("pojos") List<File> pojo);

    int update(@Param("pojo") File pojo);
}
