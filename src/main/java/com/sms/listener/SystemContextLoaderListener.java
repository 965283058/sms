package com.sms.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.sms.common.ApplicationContextHelper;


public class SystemContextLoaderListener extends ContextLoaderListener {

    public void contextInitialized(ServletContextEvent event) {
	super.contextInitialized(event);
	ApplicationContextHelper.init(event.getServletContext());
    }
}
