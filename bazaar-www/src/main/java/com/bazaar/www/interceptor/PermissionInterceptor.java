package com.bazaar.www.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gandalf.framework.constant.SymbolConstant;

public class PermissionInterceptor extends HandlerInterceptorAdapter {

    private String[]    anonymousUri;
    private PathMatcher matcher = new AntPathMatcher();

    /**
     * @param anonymousUri the anonymousUri to set
     */
    public void setAnonymousUri(String[] anonymousUri) {
        this.anonymousUri = anonymousUri;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest
     * , javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String requestURI = request.getRequestURI();
//        String contextPath = request.getContextPath();
//        requestURI = requestURI.substring(contextPath.length());
//        if(StringUtils.isEmpty(requestURI)) {
//        	requestURI = "/";
//        }
//        for (String exculdeURI : anonymousUri) {
//            if (matcher.match(exculdeURI, requestURI)) {
//                return Boolean.TRUE;
//            }
//        }
//        Object user = request.getSession().getAttribute(WebConstant.CURRENT_USER);
//        if(user == null) {//session中不存在用户信息，需要重新登录
//        	String loginUrl = buildLoginUrl(request);
//            response.sendRedirect(loginUrl);
//        	return Boolean.FALSE;
//        } else {
//        	User u = (User)user;
//        	if("admin".equalsIgnoreCase(u.getUsername())) {
//        		return true;
//        	}
//        }
//        HttpSession session = request.getSession(false);
//    	Map<String, SimpleResource> permissionMap = (Map<String, SimpleResource>)session.getAttribute("permission");
//    	SimpleResource sr = permissionMap.get(requestURI);
//        if(sr == null) {//uri不存在或者用户无权限访问
//        	response.sendError(404);
//        	return Boolean.FALSE;
//        }
//        if(!RequestUtil.isAjaxRequest(request)){
//        	long activeId = sr.getIsMenu() == 1 ? sr.getId() : sr.getBaseMenuId();
//        	request.setAttribute("activeMenuId", activeId);
//    	}
        return Boolean.TRUE;
    }
    
    private String buildLoginUrl(HttpServletRequest request) throws UnsupportedEncodingException {
    	StringBuilder sb = new StringBuilder();
        sb.append(request.getContextPath());
        sb.append("/login?redirect=");
        StringBuilder redirectUrl = new StringBuilder();
        redirectUrl.append(request.getRequestURL());
        if(StringUtils.isNotBlank(request.getQueryString())) {
        	redirectUrl.append(SymbolConstant.QUESTION);
        	redirectUrl.append(request.getQueryString());
        }
        sb.append(URLEncoder.encode(redirectUrl.toString(), "UTF-8"));
        return sb.toString();
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest
     * , javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

}