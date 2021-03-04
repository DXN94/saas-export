package com.dxn.controller.system;

import com.dxn.controller.BaseController;
import com.dxn.domain.system.Module;
import com.dxn.service.system.DeptService;
import com.dxn.service.system.ModuleService;
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
@RequestMapping("/system/module")
@Controller
public class ModuleController extends BaseController {

    @Resource
    private ModuleService moduleService;

    @Resource
    private DeptService deptService;


    /**
     * 查所有
     *
     * @param page     当前页
     * @param pageSize 页大小
     * @return PageInfo
     */
    @RequestMapping(value = "/findAll",name = "查找所有模块")
    public String findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        PageInfo pageInfo = moduleService.findAll(page, pageSize);
        request.setAttribute("page", pageInfo);
        return "/system/module/module-list";
    }

    /**
     * 转发到新增页面
     *
     * @return
     */
    @RequestMapping(value = "/toAdd",name = "跳转到新增页面")
    public String toAdd() {
        List<Module> modules = moduleService.findAll();
        request.setAttribute("menus", modules);
        return "/system/module/module-add";
    }

    /**
     * @param module 实体类
     * @return String
     */
    @RequestMapping(value = "/edit",name = "新增或删除")
    public String edit(Module module) {
        if ("".equals(module.getId()) || null == module.getId()) {
            moduleService.saveModule(module);
        } else {
            moduleService.updateModule(module);
        }
        return "redirect:/system/module/findAll.do";
    }

    /**
     * 删除
     *
     * @param module
     * @return
     */
    @RequestMapping(value = "/delete",name = "删除模块")
    public String deleteUser(Module module) {
        moduleService.deleteModule(module);
        return "redirect:/system/module/findAll.do";
    }

    /**
     * 更新
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/toUpdate",name = "跳转到更新页面")
    public String toUpdate(String id) {
        Module module = moduleService.findById(id);
        request.setAttribute("module", module);
        List<Module> modules = moduleService.findAll();
        request.setAttribute("menus",modules);
        return "/system/module/module-update";
    }

}
