package com.sms.filter;

import java.io.EOFException;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.sms.authentication.SessionManager;
import com.sms.common.ApplicationContextHelper;

@Component
public class LoginFilter implements Filter {
    @Override
    public void destroy() {
	// TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) {
	try {
	    HttpServletRequest request = (HttpServletRequest) arg0;
	    HttpServletResponse response = (HttpServletResponse) arg1;
		
	    String url = request.getRequestURI().toString();
	    String basePath = request.getContextPath();
	    if (url.equalsIgnoreCase(basePath + '/') || url.endsWith("login.jsp") || url.endsWith("global_variables")) {
		arg2.doFilter(arg0, arg1);
		return;
	    }
		
	    String sessionId = request.getSession().getId();	
	    SessionManager sessionManager = ((SessionManager)ApplicationContextHelper.getBean("sessionManager"));

	    sessionManager.insertSession(sessionId,1);//for test

		if (sessionManager.isValidSession(sessionId)) {
		arg2.doFilter(arg0, arg1);
	    } else {

		response.sendRedirect(basePath + "/jsp/login.jsp");
	    }
	}
	catch (EOFException eofException) {
	    eofException.printStackTrace();
	}
	catch (IOException ioException) {
	    ioException.printStackTrace();
	}
	catch (ServletException servletException) {
	    servletException.printStackTrace();
	}
	catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
	//sessionManager = (SessionManager)AppUtil.getBean("loginInfo");
    }
}
