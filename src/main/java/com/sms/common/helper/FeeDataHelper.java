package com.sms.common.helper;

import com.sms.model.Course;
import com.sms.model.Fee;
import com.sms.vo.CourseVO;
import com.sms.vo.FeeVO;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class FeeDataHelper {
	public static List<FeeVO> convertFeesToFeeVOs(List<Fee> fees) {
		List<FeeVO> feeVOS = new ArrayList<>();
		fees.stream().forEach(fee -> {
			feeVOS.add(convertFeeToFeeVO(fee));
		});
		return feeVOS;
	}

	public static FeeVO convertFeeToFeeVO(Fee fee) {
		FeeVO feeVO = new FeeVO();
		BeanUtils.copyProperties(fee, feeVO);
		return feeVO;
	}

	public static Fee convertFeeVOToFee(FeeVO feeVO) {
		Fee fee = new Fee();
		BeanUtils.copyProperties(feeVO, fee);
		return fee;
	}

	public static List<JSONObject> convertFeeVOsToJSONObjects(List<FeeVO> feeVOs) {
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		feeVOs.stream().forEach(feeVO -> {
			jsonObjects.add(feeVO.serializeToJSONObject());
		});
		return jsonObjects;
	}
}
