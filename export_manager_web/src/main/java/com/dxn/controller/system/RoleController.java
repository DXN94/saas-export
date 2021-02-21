package com.dxn.controller.system;

import com.dxn.controller.BaseController;
import com.dxn.domain.system.Dept;
import com.dxn.domain.system.Module;
import com.dxn.domain.system.Role;
import com.dxn.domain.system.User;
import com.dxn.service.system.DeptService;
import com.dxn.service.system.ModuleService;
import com.dxn.service.system.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/21 20:55
 */
@RequestMapping("/system/role")
@Controller
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private ModuleService moduleService;


    /**
     * 查所有
     * @param page 当前页
     * @param pageSize 页大小
     * @return PageInfo
     */
    @RequestMapping(value = "/findAll",name = "查找所有角色")
    public String findAll(@RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "10")int pageSize) {
        PageInfo pageInfo = roleService.findAll(companyId,page, pageSize);
        request.setAttribute("page",pageInfo);
        return "/system/role/role-list";
    }

    /**
     * 转发到新增页面
     * @return
     */
    @RequestMapping(value = "/toAdd",name = "跳转到新增页面")
    public String toAdd(){
        return "/system/role/role-add";
    }

    /**
     *
     * @param role 实体类
     * @return Stringro
     */
    @RequestMapping(value = "/edit",name = "新增或者更新角色")
    public String edit(Role role){
        role.setCompanyId(companyId);
        role.setCompanyName(companyName);
        if ("".equals(role.getId()) || null == role.getId()){
            roleService.saveRole(role);
        }else {
            roleService.updateRole(role);
        }
        return "redirect:/system/role/findAll.do";

    }
    /**
     * 删除
     * @param role
     * @return
     */
    @RequestMapping(value = "/delete",name = "删除角色")
    public String deleteUser(Role role){
        roleService.deleteRole(role);
        return "redirect:/system/role/findAll.do";
    }

    /**
     * 更新
     * @param id
     * @return
     */
     @RequestMapping(value = "/toUpdate",name = "跳转到更新页面")
     public String toUpdate(String id){
     Role role = roleService.findById(id);
     request.setAttribute("role",role);
     return "/system/role/role-update";
     }

    /**
     * 跳转角色权限管理
     * @param id
     * @return
     */
    @RequestMapping(value = "/roleModule",name = "跳转角色权限管理页面")
    public String roleModule(String roleid){
        Role role = roleService.findById(roleid);
        request.setAttribute("role",role);
        return "/system/role/role-module";
    }

    /**
     * 初始化权限管理
     * @param id
     * @return
     */
    @RequestMapping(value = "/initModuleData",name = "初始化权限管理")
    @ResponseBody
    public List<Map> initModuleData(String id){
        //根据角色id查出该角色所有权限
        List<String> moduleIds = moduleService.findModulesByRoleId(id);
        List<Map>list = new ArrayList();
        //查出所有权限id
        List<Module> modules = moduleService.findAll();
        for (Module module : modules) {
            HashMap map = new HashMap();
            map.put("pId",module.getParentId());
            map.put("id",module.getId());
            map.put("name",module.getName());
            //如果该角色权限id中包好某一个权限id
            if (moduleIds.contains(module.getId())){
                map.put("checked",true);
            }
            list.add(map);
        }
        return list;
    }


    /**
     * 修改角色权限
     * @param roleid
     * @param moduleIds
     * @return
     */
    @RequestMapping(value = "/updateRoleModule",name = "修改角色权限")
    public String updateRoleModule(String roleid,String moduleIds){
        roleService.updateRoleModule(roleid,moduleIds);
        return "redirect:/system/role/findAll.do";
    }
}
