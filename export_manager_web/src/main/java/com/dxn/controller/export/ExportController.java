package com.dxn.controller.export;

import com.dxn.controller.BaseController;
import com.dxn.domain.cargo.Contract;
import com.dxn.domain.export.Export;
import com.dxn.domain.export.ExportExample;
import com.dxn.domain.export.ExportProduct;
import com.dxn.domain.export.ExportProductExample;
import com.dxn.service.cargo.ContractService;
import com.dxn.service.export.ExportProductService;
import com.dxn.service.export.ExportService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    @Resource
    private ExportProductService exportProductService;


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
            export.setUpdateBy(userName);
            export.setUpdateTime(new Date());
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

    @RequestMapping(value = "toView", name = "查看报运单")
    public String watchExport(String id) {
        Export ex = exportService.findById(id);
        request.setAttribute("export", ex);
        return "/cargo/export/export-view";
    }

    @RequestMapping(value = "toUpdate", name = "前往报运单编辑页面")
    public String toUpdate(String id) {
        //根据id查询报运单
        Export export = exportService.findById(id);
        request.setAttribute("export", export);
        //根据报运单id查询报运单下的货物和附件
        ExportProductExample exportProductExample = new ExportProductExample();
        ExportProductExample.Criteria criteria = exportProductExample.createCriteria();
        criteria.andExportIdEqualTo(id);
        List<ExportProduct> exportProducts = exportProductService.findAll(exportProductExample);
        request.setAttribute("eps", exportProducts);
        return "/cargo/export/export-update";
    }

    @RequestMapping(value = "delete", name = "删除报运单")
    public String delete(String id) {
        if (!"".equals(id)) {
            exportService.delete(id);
        }
        return "redirect:/cargo/export/findAll.do";
    }


}
