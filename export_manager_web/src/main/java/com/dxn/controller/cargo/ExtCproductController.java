package com.dxn.controller.cargo;

import com.dxn.domain.cargo.*;
import com.dxn.service.cargo.ExtCproductService;
import com.dxn.service.cargo.FactoryService;
import com.dxn.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 29237
 */
@Controller
@RequestMapping("/cargo/extCproduct")
public class ExtCproductController extends BaseController{

    @Resource
    private ExtCproductService extCproductService;

    @Resource
    private FactoryService factoryService;

    @RequestMapping(value = "/list",name = "根据货物id查询所有附件列表")
    public String list(String contractId,String contractProductId,@RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "5")int size){
        //1、把2个id传递到页面
        request.setAttribute("contractId",contractId);
        request.setAttribute("contractProductId",contractProductId);

        //2、根据货物id查询附件列表
        ExtCproductExample example1 = new ExtCproductExample();
        ExtCproductExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andContractProductIdEqualTo(contractProductId);
        PageInfo info = extCproductService.findAll(example1, page, size);

        request.setAttribute("page",info);

        //3、厂家列表
        FactoryExample example = new FactoryExample();
        FactoryExample.Criteria criteria = example.createCriteria();
        criteria.andCtypeEqualTo("附件");
        List<Factory> factoryList = factoryService.findAll(example);
        request.setAttribute("factoryList",factoryList);
        //4、跳转
        return "cargo/extc/extc-list";
    }

    @RequestMapping(value = "/edit",name="修改数据")
    public String edit(ExtCproduct extCproduct){
        //调用业务
        if(StringUtils.isEmpty(extCproduct.getId())){
            extCproductService.save(extCproduct);
        }else{
            extCproductService.update(extCproduct);
        }
        //3、跳转
        return "redirect:/cargo/extCproduct/list.do?contractId="+extCproduct.getContractId()+"&contractProductId="+extCproduct.getContractProductId();
    }




    @RequestMapping(value = "/delete",name="修改数据")
    public String delete(String id,String contractId,String contractProductId){
        //调用业务
        extCproductService.delete(id);
        //跳转
        return "redirect:/cargo/extCproduct/list.do?contractId="+contractId+"&contractProductId="+contractProductId;
    }


    @RequestMapping(value = "/toUpdate",name="根据附件id回显数据")
    public String toUpdate(String id){
        //1、根据货物id，查询货物信息
        ExtCproduct product = extCproductService.findById(id);
        request.setAttribute("extCproduct",product);

        //2、查询货物的厂家列表
        FactoryExample example = new FactoryExample();
        FactoryExample.Criteria criteria = example.createCriteria();
        criteria.andCtypeEqualTo("附件");
        List<Factory> factoryList = factoryService.findAll(example);
        request.setAttribute("factoryList",factoryList);

        //3、跳转
        return "cargo/extc/extc-update";
    }


}
