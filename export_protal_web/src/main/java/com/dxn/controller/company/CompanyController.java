package com.dxn.controller.company;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dxn.controller.BaseController;
import com.dxn.domain.company.Company;
import com.dxn.service.company.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/21 20:55
 */
@Controller
public class CompanyController extends BaseController {

    @Reference
    private CompanyService companyService;

    /**
     * @param company 实体类
     * @return String
     */
    @RequestMapping(value = "/apply", name = "新增企业")
    @ResponseBody
    public String save(Company company) {
        try {
            companyService.saveCompany(company);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "2";
        }

    }

}
