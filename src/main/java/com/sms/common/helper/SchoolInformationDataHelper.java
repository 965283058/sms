package com.sms.common.helper;

import com.sms.model.Information;
import com.sms.model.SchoolInformation;
import com.sms.vo.InformationVO;
import com.sms.vo.SchoolInformationVO;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class SchoolInformationDataHelper {

	public static List<SchoolInformationVO> convertSchoolInformationsToSchoolInformationVOs(List<SchoolInformation> schoolInformationList){
		List<SchoolInformationVO> list = new ArrayList<>();
		schoolInformationList.stream().forEach(information -> {
			list.add(convertSchoolInformationToSchoolInformationVO(information));
		});
		return list;
	}
	public static SchoolInformationVO convertSchoolInformationToSchoolInformationVO(SchoolInformation information) {
		SchoolInformationVO vo = new SchoolInformationVO();
		BeanUtils.copyProperties(information,vo);
		return vo;
	}

	public static SchoolInformation convertSchoolInformationVoToSchoolInformation(SchoolInformationVO informationVO){
		SchoolInformation information = new SchoolInformation();
		BeanUtils.copyProperties(informationVO,information);
		return information;
	}

	public static List<JSONObject> convertSchoolInformationVOsToJSONObjects(List<SchoolInformationVO> informationVOS) {
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		informationVOS.stream().forEach(informationVO -> {
			jsonObjects.add(informationVO.serializeToJSONObject());
		});
		return jsonObjects;
	}
}
