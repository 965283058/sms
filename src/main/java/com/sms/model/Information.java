package com.sms.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Information {
    private Integer id;

    private String title;

    private Integer informationTypeId;

    private String informationTypeName;

    private Integer informationSubtypeId;

    private String informationSubtypeName;

    private Integer userId;

    private String userName;

    private Date createdTime;

    private String contentFilePath;

    private String photoUrl;

    private Integer schoolId;

    private String schoolName;

    private Integer branchSchoolId;

    private String branchSchoolName;

    private Integer managementStatusId;

}