package com.sms.common;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private static ServletContext servletContext;

    public static void init(ServletContext _servletContext) {
	servletContext = _servletContext;
    }

    public void setApplicationContext(ApplicationContext contex) throws BeansException {
	applicationContext = contex;
    }

    public static ApplicationContext getContext() {
	return applicationContext;
    }

    public static ServletContext getServletContext() throws Exception {
	return servletContext;
    }

    public static Object getBean(Class<?> cls) {
	return applicationContext.getBean(cls);
    }

    public static Object getBean(String beanId) {
	return applicationContext.getBean(beanId);
    }

    public static String getAppAbsolutePath() {
	return servletContext.getRealPath("/");
    }

    public static String getRealPath(String path) {
	return servletContext.getRealPath(path);
    }

    public static String getClasspath() {
	String classPath = Thread.currentThread().getContextClassLoader().getResource("applicationContext.xml")
		.getPath();
	String rootPath = "";
	if ("\\".equals(File.separator)) {
	    rootPath = classPath.substring(1);
	    rootPath = rootPath.replace("/", "\\");
	}

	if ("/".equals(File.separator)) {
	    rootPath = classPath.substring(1);
	    rootPath = rootPath.replace("\\", "/");
	}
	return rootPath;
    }

}
