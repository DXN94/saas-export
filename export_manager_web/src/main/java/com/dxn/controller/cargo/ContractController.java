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
import java.util.List;

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

    @RequestMapping("/findAll")
    public String findAll(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "5") int pageSize){
        ContractExample contractExample = new ContractExample();
        contractExample.setOrderByClause("create_time desc");
        ContractExample.Criteria criteria = contractExample.createCriteria();
        // 以公司id查询
        criteria.andCompanyIdEqualTo(companyId);
        PageInfo info = contractService.findAll(contractExample,pageNum,pageSize);
        // 将查询的结果放入req域中
        request.setAttribute("page",info);
        return "/cargo/contract/contract-list";
    }

}
