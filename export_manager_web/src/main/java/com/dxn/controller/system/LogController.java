package com.dxn.controller.system;

import com.dxn.controller.BaseController;
import com.dxn.domain.system.Dept;
import com.dxn.domain.system.Role;
import com.dxn.domain.system.User;
import com.dxn.service.system.DeptService;
import com.dxn.service.system.RoleService;
import com.dxn.service.system.SysLogService;
import com.dxn.service.system.UserService;
import com.github.pagehelper.PageInfo;
import org.eclipse.jetty.security.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/21 20:55
 */
@RequestMapping("/system/log")
@Controller
public class LogController extends BaseController {

    @Autowired
    private SysLogService sysLogService;


    /**
     * 查所有
     * @param page 当前页
     * @param pageSize 页大小
     * @return PageInfo
     */
    @RequestMapping(value = "/findAll",name = "查找所有日志")
    public String findAll(@RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "10")int pageSize) {
        PageInfo pageInfo = sysLogService.findAll(companyId,page, pageSize);
        request.setAttribute("page",pageInfo);
        return "/system/log/log-list";
    }



}
