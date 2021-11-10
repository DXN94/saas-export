package com.dxn.controller.system;

import com.dxn.controller.BaseController;
import com.dxn.domain.system.Dept;
import com.dxn.service.system.DeptService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/21 20:55
 */
@RequestMapping("/system/dept")
@Controller
public class DeptController extends BaseController {

    @Resource
    private DeptService deptService;


    /**
     * 查所有
     * @param page 当前页
     * @param pageSize 页大小
     * @return PageInfo
     */
    @RequestMapping(value = "/findAll",name = "查找所有部门")
    public String findAll(@RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "10")int pageSize) {
        PageInfo pageInfo = deptService.findAll(companyId,page, pageSize);
        request.setAttribute("page",pageInfo);
        return "/system/dept/dept-list";
    }

    /**
     * 转发到新增页面
     * @return String
     */
    @RequestMapping(value = "/toAdd",name = "跳转到新增页面")
    public String toAdd(){
        List<Dept> depts = deptService.findAll(companyId);
        request.setAttribute("deptList",depts);
        return "/system/dept/dept-add(1)";
    }

    /**
     *
     * @param dept 实体类
     * @return String
     */
    @RequestMapping(value = "/edit",name = "新增或更新部门")
    public String edit(Dept dept){

        if ("".equals(dept.getId()) || null == dept.getId()){
            dept.setCompanyId(companyId);
            dept.setCompanyName(companyName);
            deptService.saveDept(dept);
        }else {
            deptService.updateDept(dept);
        }
        return "redirect:/system/dept/findAll.do";

    }

    /**
     * 删除操作
     * @param dept 实体类
     * @return String
     */
    @RequestMapping(value = "/delete",name = "删除部门")
    public String deleteDept(Dept dept){
        deptService.deleteDept(dept);
        return "redirect:/system/dept/findAll.do";
    }

    /**
     * 跳转到更新页面
     * @param id id
     * @return String
     */
    @RequestMapping(value = "/toUpdate",name = "跳转到更新页面")
    public String toUpdate(String id){
        Dept dept = deptService.findById(id);
        request.setAttribute("dept",dept);
        List<Dept> depts = deptService.findAll(companyId);
        request.setAttribute("deptList",depts);
        return "/system/dept/dept-update";
    }

}
