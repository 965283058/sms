package com.sms.vo;

import com.sms.common.helper.DateTimeHelper;
import lombok.Data;
import lombok.ToString;
import net.sf.json.JSONObject;

import java.util.Date;

@Data
@ToString
public class InformationVO {
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

	public JSONObject serializeToJSONObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id",id);
		jsonObject.put("title",title);
		jsonObject.put("information_type_id",informationTypeId);
		jsonObject.put("information_type_name",informationTypeName);
		jsonObject.put("information_subtype_id",informationSubtypeId);
		jsonObject.put("information_subtype_name",informationSubtypeName);
		jsonObject.put("user_id",userId);
		jsonObject.put("user_name",userName);
		jsonObject.put("created_time",createdTime);
		jsonObject.put("content_file_path",contentFilePath);
		jsonObject.put("photo_url",photoUrl);
		jsonObject.put("school_id",schoolId);
		jsonObject.put("school_name",schoolName);
		jsonObject.put("branch_school_id",branchSchoolId);
		jsonObject.put("branch_school_name",branchSchoolName);
		jsonObject.put("management_status_id",managementStatusId);
		return jsonObject;
	}

}
