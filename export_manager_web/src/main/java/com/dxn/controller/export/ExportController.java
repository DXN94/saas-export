package com.dxn.controller.export;

import com.dxn.controller.BaseController;
import com.dxn.domain.cargo.Contract;
import com.dxn.domain.export.Export;
import com.dxn.domain.export.ExportExample;
import com.dxn.service.cargo.ContractService;
import com.dxn.service.export.ExportService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author dxn
 * @date 2021年11月29日10:28 下午
 */
@Controller
@RequestMapping("/cargo/export/")
public class ExportController extends BaseController {

    @Resource
    private ContractService contractService;
    @Resource
    private ExportService exportService;

    @RequestMapping(value = "contractList", name = "查询已提交的合同")
    public String contractList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize) {
        PageInfo<Contract> info = contractService.selectConEqSt(page, pageSize, companyId);
        request.setAttribute("page", info);
        return "/cargo/export/export-contractList";
    }

    @RequestMapping(value = "/toExport", name = "前往报运页面")
    public String toExport(String id) {
        request.setAttribute("id", id);
        return "/cargo/export/export-toExport";
    }

    @RequestMapping(value = "edit", name = "新增或者更新报运单")
    public String edit(Export export) {
        //判断有没有id，有则更新，没有则新增
        if (export.getId() == null || "".equals(export.getId())) {
            export.setCompanyId(companyId);
            export.setCompanyName(companyName);
            export.setCreateBy(userName);
            export.setCreateDept(deptName);
            exportService.save(export);
        } else {
            exportService.update(export);
        }
        return "redirect:/cargo/export/findAll.do";
    }

    @RequestMapping(value = "findAll", name = "出口报运")
    public String findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize) {
        ExportExample exportExample = new ExportExample();
        ExportExample.Criteria criteria = exportExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        PageInfo info = exportService.findAll(exportExample, page, pageSize);
        request.setAttribute("page", info);
        return "/cargo/export/export-list";
    }


}
