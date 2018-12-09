package cn.jkj521.bookstore.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jkj521.bookstore.util.redis.HostUtil;
import cn.yunzhf.accounting.user.entity.UzUser;

public class MyFilter implements Filter{

	private static final String FILTERED_REQUEST = "@@session_context_filtered_request";


	private static final String[] INHERENT_ESCAPE_URIS = {"/","error", "logout","getsign", "index" };

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpRespose = (HttpServletResponse) response;
		UzUser user = (UzUser) httpRequest.getSession().getAttribute("user");
		//Renter currentRenter = (Renter) httpRequest.getSession().getAttribute("CurrentRenter");
		
		HttpServletResponse _response = (HttpServletResponse) response;
		_response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		
		if (request != null && request.getAttribute(FILTERED_REQUEST) != null) {
			filterChain.doFilter(request, response);
		} else {

			request.setAttribute(FILTERED_REQUEST, Boolean.TRUE);

			if (user == null && !isURILogin(httpRequest.getRequestURI(), httpRequest)) {
				if ( !isURILogin(httpRequest.getRequestURI(), httpRequest)) {
					//========================
					httpRespose.sendRedirect(HostUtil.host + "AccountingOnline/user/checkLogin?url=bookstore/getsign");
					return ;
				}			
			}
			filterChain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	
	private boolean isURILogin(String requestURI, HttpServletRequest request) {
		if (request.getContextPath().equalsIgnoreCase(requestURI) || (request.getContextPath() + "/").equalsIgnoreCase(requestURI))
			return true;
		for (String uri : INHERENT_ESCAPE_URIS) {
			
			if (requestURI != null && requestURI.indexOf(uri) >= 0) {
				return true;
			}
		}
		return false;
	}
}
