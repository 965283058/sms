package com.sms.common.helper;

import com.sms.model.Course;
import com.sms.model.Courseware;
import com.sms.vo.CourseVO;
import com.sms.vo.CoursewareVO;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CoursewareDataHelper {
	public static List<CoursewareVO> convertCoursewaresToCoursewareVOs(List<Courseware> coursewares) {
		List<CoursewareVO> coursewareVOS = new ArrayList<>();
		coursewares.stream().forEach(courseware -> {
			coursewareVOS.add(convertCoursewareToCoursewareVO(courseware));
		});
		return coursewareVOS;
	}

	public static CoursewareVO convertCoursewareToCoursewareVO(Courseware courseware) {
		CoursewareVO coursewareVO = new CoursewareVO();
		BeanUtils.copyProperties(courseware, coursewareVO);
		return coursewareVO;
	}

	public static Courseware convertCoursewareVOToCourseware(CoursewareVO coursewareVO) {
		Courseware courseware = new Courseware();
		BeanUtils.copyProperties(coursewareVO, courseware);
		return courseware;
	}

	public static List<JSONObject> convertCoursewaresVOsToJSONObjects(List<CoursewareVO> coursewareVOs) {
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		coursewareVOs.stream().forEach(coursewareVO -> {
			jsonObjects.add(coursewareVO.serializeToJSONObject());
		});
		return jsonObjects;
	}
}
