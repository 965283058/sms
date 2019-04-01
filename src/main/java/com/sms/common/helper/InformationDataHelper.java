package com.sms.common.helper;

import com.sms.model.Information;
import com.sms.vo.InformationVO;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class InformationDataHelper {

	public static List<InformationVO> convertInformationsToInformationVOs(List<Information> informationList){
		List<InformationVO> list = new ArrayList<>();
		informationList.stream().forEach(information -> {
			list.add(convertInformationToInformationVO(information));
		});
		return list;
	}
	public static InformationVO convertInformationToInformationVO(Information information) {
		InformationVO vo = new InformationVO();
		BeanUtils.copyProperties(information,vo);
		return vo;
	}

	public static Information convertInformationVoToInformation(InformationVO informationVO){
		Information information = new Information();
		BeanUtils.copyProperties(informationVO,information);
		return information;
	}

	public static List<JSONObject> convertInformationVOsToJSONObjects(List<InformationVO> informationVOS) {
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		informationVOS.stream().forEach(informationVO -> {
			jsonObjects.add(informationVO.serializeToJSONObject());
		});
		return jsonObjects;
	}
}
