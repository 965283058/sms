package com.sms.common.helper;

import java.util.ArrayList;
import java.util.List;

import com.sms.model.Monitor;
import com.sms.vo.MonitorVO;

import net.sf.json.JSONObject;

public class MonitorDataHelper {
	public static List<MonitorVO> convertMonitorsToMonitorVOs(List<Monitor> monitors) {
		List<MonitorVO> monitorVOs = new ArrayList<MonitorVO>();
		monitors.stream().forEach(g -> {
			monitorVOs.add(convertMonitorToMonitorVO(g));
		});
		return monitorVOs;
	}

	public static MonitorVO convertMonitorToMonitorVO(Monitor monitor) {
	    	MonitorVO monitorVO = new MonitorVO();
		monitorVO.setId(monitor.getId());
		monitorVO.setName(monitor.getName());
		monitorVO.setInstallDate(monitor.getInstallDate());
		monitorVO.setCameraAddress(monitor.getCameraAddress());
		monitorVO.setPhotoUrl(monitor.getPhotoUrl());
		monitorVO.setDescription(monitor.getDescription());
		monitorVO.setGroupId(monitor.getGroupId());
		return monitorVO;
	}

	public static Monitor convertMonitorVOToMonitor(MonitorVO monitorVO) {
	    	Monitor monitor = new Monitor();
		if (monitorVO.getNameIsPresented()) {
			monitor.setName(monitorVO.getName());
		}
		if (monitorVO.getInstallDateIsPresented()) {
		    monitor.setInstallDate(monitorVO.getInstallDate());
		}
		if (monitorVO.getCameraAddressIsPresented()) {
			monitor.setCameraAddress(monitorVO.getCameraAddress());
		}
		if (monitorVO.getPhotoUrlIsPresented()) {
			monitor.setPhotoUrl(monitorVO.getPhotoUrl());
		}
		if (monitorVO.getDescriptionIsPresented()) {
			monitor.setDescription(monitorVO.getDescription());
		}
		if (monitorVO.getGroupIdIsPresented()) {
			monitor.setGroupId(monitorVO.getGroupId());
		}
		return monitor;
	}

	public static Monitor generateUpdatedMonitor(Monitor existingMonitor, MonitorVO monitorVO) {
	    	Monitor monitor = new Monitor();
		monitor.setId(existingMonitor.getId());
		monitor.setName(monitorVO.getNameIsPresented() ? monitorVO.getName() : existingMonitor.getName());
		monitor.setInstallDate(monitorVO.getInstallDateIsPresented() ? monitorVO.getInstallDate() : existingMonitor.getInstallDate());
		monitor.setCameraAddress(monitorVO.getCameraAddressIsPresented() ? monitorVO.getCameraAddress() : existingMonitor.getCameraAddress());
		monitor.setPhotoUrl(monitorVO.getPhotoUrlIsPresented() ? monitorVO.getPhotoUrl() : existingMonitor.getPhotoUrl());
		monitor.setDescription(monitorVO.getDescriptionIsPresented() ? monitorVO.getDescription() : existingMonitor.getDescription());
		monitor.setGroupId(monitorVO.getGroupIdIsPresented() ? monitorVO.getGroupId() : existingMonitor.getGroupId());
		return monitor;
	}

	public static List<JSONObject> convertMonitorVOsToJSONObjects(List<MonitorVO> monitorVOs) {
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		monitorVOs.stream().forEach(monitorVO -> {
			jsonObjects.add(monitorVO.serializeToJSONObject());
		});
		return jsonObjects;
	}
}
