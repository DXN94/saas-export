package com.dxn.controller;

import com.dxn.domain.system.User;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/22 17:00
 */
public class BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    protected String companyId;
    protected String companyName;
    protected String userName;
    protected String deptName;
    protected String deptId;
    protected Integer degree;
    protected String userId;

    @ModelAttribute
    public void setReqAndResp(HttpServletRequest request, HttpServletResponse response,
                              HttpSession session) {
        this.request = request;
        this.response = response;
        this.session = session;
        User user = (User) session.getAttribute("user");
        if (null != user){
            this.companyId = user.getCompanyId();
            this.companyName = user.getCompanyName();
            this.userName = user.getUserName();
            this.deptName = user.getDeptName();
            this.deptId = user.getDeptId();
            this.degree = user.getDegree();
            this.userId = user.getId();
        }
    }

}
