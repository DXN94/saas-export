package com.dxn.controller.cargo;

import com.dxn.controller.BaseController;
import com.dxn.domain.cargo.ContractProduct;
import com.dxn.domain.cargo.ContractProductExample;
import com.dxn.domain.cargo.Factory;
import com.dxn.domain.cargo.FactoryExample;
import com.dxn.service.cargo.ContractProductService;
import com.dxn.service.cargo.FactoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/3/11 20:48
 */
@Controller
@RequestMapping("/cargo/contractProduct")
public class ContractProductController extends BaseController {

    @Resource
    private ContractProductService contractProductService;
    @Resource
    private FactoryService factoryService;

    @RequestMapping(value = "/list", name = "根据合同id查询附属所有货物")
    public String findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, String contractId) {
        //1.将合同id返回页面
        request.setAttribute("contractId", contractId);
        //2.查询所有厂家
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria factoryExampleCriteria = factoryExample.createCriteria();
        factoryExampleCriteria.andCtypeEqualTo("货物");
        List<Factory> factories = factoryService.findAll(factoryExample);
        request.setAttribute("factoryList", factories);
        //3.根据合同id查询附属所有货物
        ContractProductExample contractProductExample = new ContractProductExample();
        ContractProductExample.Criteria contractProductExampleCriteria = contractProductExample.createCriteria();
        contractProductExampleCriteria.andContractIdEqualTo(contractId);
        PageInfo info = contractProductService.findAll(contractProductExample, page, pageSize);
        request.setAttribute("page", info);
        return "/cargo/product/product-list";
    }

    @RequestMapping("/edit")
    public String edit(ContractProduct contractProduct) {
        if (StringUtils.isEmpty(contractProduct.getId())) {
            //如果没有id则是新增
            contractProduct.setCompanyId(companyId);
            contractProduct.setCompanyName(companyName);
            contractProductService.save(contractProduct);
        } else {
            contractProductService.update(contractProduct);
        }
        return "redirect:/cargo/contractProduct/list.do?contractId=" + contractProduct.getContractId();
    }

    @RequestMapping(value = "/toUpdate", name = "前往货物修改页面")
    public String toUpdate(String id) {
        //1.查询所有生产厂家,回显
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria factoryExampleCriteria = factoryExample.createCriteria();
        factoryExampleCriteria.andCtypeEqualTo("货物");
        List<Factory> factories = factoryService.findAll(factoryExample);
        request.setAttribute("factoryList", factories);
        //2.根据合同id查询该货物对象
        ContractProduct contractProduct = contractProductService.findById(id);
        request.setAttribute("contractProduct", contractProduct);
        return "/cargo/product/product-update";
    }


//    @RequestMapping(value = "/edit", name = "修改数据")
//    public String edit(ContractProduct contractProduct, MultipartFile productPhoto) throws Exception {
//        //调用业务
//        if (StringUtils.isEmpty(contractProduct.getId())) {
//
////            if (productPhoto != null) {
//////                String img = "http://" + fileUploadUtil.upload(productPhoto);
////                contractProduct.setProductImage(img);
////            }
//
//            contractProductService.save(contractProduct);
//        } else {
//            contractProductService.update(contractProduct);
//        }
//        //3、跳转
//        return "redirect:/cargo/contractProduct/list.do?contractId=" + contractProduct.getContractId();
//    }


    @RequestMapping(value = "/delete", name = "修改数据")
    public String delete(String id, String contractId) {

        //调用业务
        contractProductService.delete(id);
        //跳转
        return "redirect:/cargo/contractProduct/list.do?contractId=" + contractId;
    }
}
