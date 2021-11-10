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

    private static final int DEGREE2 = 2;
    private static final int DEGREE3 = 3;
    private static final int DEGREE4 = 4;

    @RequestMapping(value = "/findAll",name = "查询所有购销合同")
    public String findAll(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10") int pageSize){
        ContractExample contractExample = new ContractExample();
        contractExample.setOrderByClause("create_time desc");
        ContractExample.Criteria criteria = contractExample.createCriteria();
        // 以公司id查询
        criteria.andCompanyIdEqualTo(companyId);
        /**
         * 2-管理所有下属部门和人员 部门设计 100 100101 100101101 根据模糊查询
         * 3-管理本部门 根据部门id查询
         * 4-普通员工 根据员工id查询
         */
        if (degree == DEGREE4){
            criteria.andCreateByEqualTo(userId);
        }else if (degree == DEGREE3){
            criteria.andCreateDeptLike(deptId+"%");
        }else{
            //模糊查询
            criteria.andCreateDeptLike(deptId+"%");
        }
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
                contract.setCreateBy(userId);
                contract.setCreateDept(deptId);
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

    @RequestMapping(value = "/submit", name = "购销合同提交")
    public String submit(String id){
        //根据合同id动态修改状态
        //1是已上报
        int state = 1;
        int i = contractService.updateStatusById(id,state);
        return "redirect:/cargo/contract/findAll.do";
    }

    @RequestMapping(value = "/cancel", name = "购销合同取消")
    public String cancel(String id){
        //根据合同id动态修改状态
        //0是草稿
        int state = 0;
        int i = contractService.updateStatusById(id, state);
        return "redirect:/cargo/contract/findAll.do";
    }
}
