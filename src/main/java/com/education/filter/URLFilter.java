package com.education.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class URLFilter
 */
public class URLFilter implements Filter {

    /**
     * Default constructor. 
     */
    public URLFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req; 
		HttpServletResponse response = (HttpServletResponse) rep;
		StringBuffer url1=request.getRequestURL();
		String url = url1.toString();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginid") != null){
			
			chain.doFilter(req, rep);
		}
		else{
			if(url.contains("loginin")){
				response.sendError(404);
			}else{
				chain.doFilter(req, rep);
			}
			
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
