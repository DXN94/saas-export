package com.dxn.controller.system;

import com.dxn.controller.BaseController;
import com.dxn.domain.system.Dept;
import com.dxn.domain.system.Role;
import com.dxn.domain.system.User;
import com.dxn.service.system.DeptService;
import com.dxn.service.system.RoleService;
import com.dxn.service.system.UserService;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/system/user")
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;


    /**
     * 查所有
     * @param page 当前页
     * @param pageSize 页大小
     * @return PageInfo
     */
    @RequestMapping(value = "/findAll",name = "查找所有用户")
    public String findAll(@RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "10")int pageSize) {
        PageInfo pageInfo = userService.findAll(companyId,page, pageSize);
        request.setAttribute("page",pageInfo);
        return "/system/user/user-list";
    }

    /**
     * 转发到新增页面
     * @return
     */
    @RequestMapping(value = "/toAdd",name = "用户跳转到新增页面")
    public String toAdd(){

        //查找所有部门回显
        List<Dept> depts = deptService.findAll(companyId);
        request.setAttribute("deptList",depts);
        request.setAttribute("user",new User());
        return "/system/user/user-add";
    }

    /**
     *
     * @param user 实体类
     * @return String
     */
    @RequestMapping(value = "/edit",name = "新增或者更新用户")
    public String edit(User user){
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);
        if ("".equals(user.getId()) || null == user.getId()){
            userService.saveUser(user);
        }else {
            userService.updateUser(user);
        }
        return "redirect:/system/user/findAll.do";

    }
    /**
     * 删除
     * @param User
     * @return
     */
    @RequestMapping(value = "/delete",name = "删除用户")
    public String deleteUser(User user){
        userService.deleteUser(user);
        return "redirect:/system/user/findAll.do";
    }

    /**
     * 更新
     * @param id
     * @return
     */
     @RequestMapping(value = "/toUpdate",name = "跳转到更新用户界面")
     public String toUpdate(String id){
     User user = userService.findById(id);
     request.setAttribute("user",user);
     List<Dept> depts = deptService.findAll(companyId);
     request.setAttribute("deptList",depts);
     return "/system/user/user-update";
     }

    /**
     * 用户管理回显
     * @param id
     * @return
     */
    @RequestMapping(value = "/roleList",name = "用户管理回显")
    public String roleList(String id){
        //查找该用户的角色
        List<Role> userRoles = roleService.findUserRoleByUserId(id);
        String userRoleStr = "";
        for (Role userRole : userRoles) {
            userRoleStr += userRole.getId() + ",";
        }
        request.setAttribute("userRoleStr",userRoleStr);
        User user = userService.findById(id);
        request.setAttribute("user",user);
        List<Role> roles = roleService.findAll(companyId);
        request.setAttribute("roleList",roles);
        return "/system/user/user-role";
    }

    /**
     * 用户角色管理更新
     * @param id
     * @return
     */
    @RequestMapping(value = "/changeRole",name = "用户角色管理更新")
    public String changeRole(@RequestParam("userid") String userId,@RequestParam("roleIds") String roleIds){
        userService.updateUserRole(userId,roleIds);
        return "redirect:/system/user/findAll.do";
    }

}
