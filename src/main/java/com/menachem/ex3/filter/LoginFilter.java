package com.menachem.ex3.filter;

import com.menachem.ex3.bean.SessionBean;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class it's the filter that not allow access to protected pages
 */
public class LoginFilter implements HandlerInterceptor {

    /**
     * Define the resource function name that return new Session Bean.
     * To SessionBean variable
     */
    @Resource(name = "MySessionBean")
    private SessionBean sessionBean;

    /**
     * This function manage the access to GET methods.
     * @param request
     * @param response
     * @param handler
     * @return true or false depend on the access approval.
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        sessionBean = (SessionBean) request.getSession().getAttribute("sessionStatus");
        if(sessionBean == null){
            sessionBean = new SessionBean();
            request.getSession().setAttribute("sessionStatus", sessionBean);
        }
        if(!sessionBean.isSessionStatus() && !request.getRequestURI().equals("/login")){
            response.sendRedirect("/login");
            return false;
        }else if(sessionBean.isSessionStatus() && request.getRequestURI().equals("/login")){
            response.sendRedirect("/");
            return false;
        }else if(!sessionBean.isSessionStatus() && request.getRequestURI().equals("/search")){
            return true;
        }
        return true;
    }
}
