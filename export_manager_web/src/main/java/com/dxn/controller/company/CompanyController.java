package com.dxn.controller.company;

import com.dxn.controller.BaseController;
import com.dxn.domain.company.Company;
import com.dxn.service.company.CompanyService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/21 20:55
 */
@RequestMapping("/company")
@Controller
public class CompanyController extends BaseController {

    @Autowired
    private CompanyService companyService;


    /**
     * 查所有
     * @param page 当前页
     * @param pageSize 页大小
     * @return PageInfo
     */
    @RequestMapping(value = "/findAll",name = "查询所有企业")
    public String findAll(@RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "10")int pageSize) {
        PageInfo pageInfo = companyService.findAll(page, pageSize);
        request.setAttribute("page",pageInfo);
        return "/company/company-list";
    }

    /**
     * 转发到新增页面
     * @return String
     */
    @RequestMapping(value = "/toAdd",name = "转发到新增企业页面")
    public String toAdd(){
        return "/company/company-add";
    }

    /**
     *
     * @param company 实体类
     * @return String
     */
    @RequestMapping(value = "/edit",name = "新增或更新企业")
    public String edit(Company company){
        if ("".equals(company.getId()) || null == company.getId()){
            companyService.saveCompany(company);
        }else {
            companyService.updateCompany(company);
        }
        return "redirect:/company/findAll.do";

    }

    /**
     * 删除操作
     * @param company 实体类
     * @return String
     */
    @RequestMapping(value = "/delete",name = "删除企业")
    public String deleteCompany(Company company){
        companyService.deleteCompany(company);
        return "redirect:/company/findAll.do";
    }

    /**
     * 跳转到更新页面
     * @param id id
     * @return String
     */
    @RequestMapping(value = "/toUpdate",name = "跳转到更新页面")
    public String toUpdate(String id){
        Company company = companyService.findById(id);
        request.setAttribute("company",company);
        return "/company/company-update";
    }

}
