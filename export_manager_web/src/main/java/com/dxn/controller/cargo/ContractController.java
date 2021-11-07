package com.dxn.controller.cargo;

import com.dxn.controller.BaseController;
import com.dxn.domain.cargo.Contract;
import com.dxn.domain.cargo.ContractExample;
import com.dxn.service.cargo.ContractService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/3/11 20:48
 */
@Controller
@RequestMapping("/cargo/contract")
public class ContractController extends BaseController {

    @Resource
    private ContractService contractService;

    @RequestMapping(value = "/findAll",name = "查询所有购销合同")
    public String findAll(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10") int pageSize){
        ContractExample contractExample = new ContractExample();
        contractExample.setOrderByClause("create_time desc");
        ContractExample.Criteria criteria = contractExample.createCriteria();
        // 以公司id查询
        criteria.andCompanyIdEqualTo(companyId);
        PageInfo info = contractService.findAll(contractExample,page,pageSize);
        // 将查询的结果放入req域中
        request.setAttribute("page",info);
        return "/cargo/contract/contract-list";
    }

    @RequestMapping(value = "/toAdd", name = "前往购销合同新增页面")
    public String toAdd(){
        return "/cargo/contract/contract-add";
    }

    @RequestMapping(value = "/edit", name = "新增/编辑购销合同")
    public String edit(Contract contract){
        if ("".equals(contract.getId()) || null == contract.getId()){
            try {
                //新增
                contract.setCreateBy(userName);
                contract.setCreateDept(deptName);
                contract.setCompanyId(companyId);
                contract.setCompanyName(companyName);
                int insert = contractService.insert(contract);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            int i = contractService.updateByPrimaryKey(contract);
        }
        return "redirect:/cargo/contract/findAll.do";
    }

    @RequestMapping(value = "/toUpdate", name = "前往购销合同编辑页面")
    public String toUpdate(String id){
        //根据合同id查询合同回显
        Contract contract = contractService.selectByPrimaryKey(id);
        request.setAttribute("contract",contract);
        return "/cargo/contract/contract-update";
    }

    @RequestMapping(value = "/delete", name = "删除购销合同")
    public String delete(String id){
        //根据合同id查询合同回显
        int i = contractService.deleteByPrimaryKey(id);
        return "redirect:/cargo/contract/findAll.do";
    }

    @RequestMapping(value = "/toView", name = "查看购销合同")
    public String toView(String id){
        //根据合同id查询合同回显
        Contract contract = contractService.selectByPrimaryKey(id);
        request.setAttribute("contract",contract);
        return "/cargo/contract/contract-view";
    }
}
