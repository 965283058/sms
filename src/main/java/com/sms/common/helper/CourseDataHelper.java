package com.sms.common.helper;

import com.sms.model.Course;
import com.sms.vo.CourseVO;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CourseDataHelper {
	public static List<CourseVO> convertCoursesToCourseVOs(List<Course> courses) {
		List<CourseVO> courseVOS = new ArrayList<>();
		courses.stream().forEach(course -> {
			courseVOS.add(convertCourseToCourseVO(course));
		});
		return courseVOS;
	}

	public static CourseVO convertCourseToCourseVO(Course course) {
		CourseVO courseVO = new CourseVO();
		BeanUtils.copyProperties(course, courseVO);
		return courseVO;
	}

	public static Course convertCourseVOToCourse(CourseVO courseVO) {
		Course course = new Course();
		BeanUtils.copyProperties(courseVO, course);
		return course;
	}

	public static List<JSONObject> convertCoursesVOsToJSONObjects(List<CourseVO> courseVOs) {
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		courseVOs.stream().forEach(courseVO -> {
			jsonObjects.add(courseVO.serializeToJSONObject());
		});
		return jsonObjects;
	}
}
