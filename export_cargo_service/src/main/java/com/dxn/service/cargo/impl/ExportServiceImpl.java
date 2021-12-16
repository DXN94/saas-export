package com.dxn.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dxn.dao.cargo.ContractDao;
import com.dxn.dao.cargo.ContractProductDao;
import com.dxn.dao.cargo.ExtCproductDao;
import com.dxn.dao.export.ExportDao;
import com.dxn.dao.export.ExportProductDao;
import com.dxn.dao.export.ExtEproductDao;
import com.dxn.domain.cargo.*;
import com.dxn.domain.export.*;
import com.dxn.domain.export.vo.ExportProductResult;
import com.dxn.domain.export.vo.ExportProductVo;
import com.dxn.domain.export.vo.ExportResult;
import com.dxn.domain.export.vo.ExportVo;
import com.dxn.service.export.ExportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * @author dxn
 * @date 2021年12月06日9:58 下午
 */
@Service
public class ExportServiceImpl implements ExportService {

    @Resource
    private ExportDao exportDao;
    @Resource
    private ContractDao contractDao;
    @Resource
    private ContractProductDao contractProductDao;
    @Resource
    private ExtCproductDao extCproductDao;
    @Resource
    private ExportProductDao exportProductDao;
    @Resource
    private ExtEproductDao extEproductDao;

    @Override
    public Export findById(String id) {
        Export export = exportDao.selectByPrimaryKey(id);
        return export;
    }

    @Override
    public void save(Export export) {
        String[] contractIds = export.getContractIds().split(",");
        //1.保存报运单
        export.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        export.setCreateTime(new Date());
        export.setState(0);
        export.setInputDate(new Date());
        //设置报运单下的货物和附件数量
        //根据报运单下的购销合同id查询
        ContractExample contractExample = new ContractExample();
        ContractExample.Criteria contractExampleCriteria = contractExample.createCriteria();
        contractExampleCriteria.andIdIn(Arrays.asList(contractIds));
        List<Contract> contracts = contractDao.selectByExample(contractExample);
        int exportPNum = 0;
        int exportENum = 0;
        StringBuilder sb = new StringBuilder();
        for (Contract contract : contracts) {
            //合同书号
            String contractNo = contract.getContractNo();
            sb.append(contractNo);
            //货物数量和附件循环➕
            exportPNum += contract.getProNum();
            exportENum += contract.getExtNum();
            //设置购销合同状态为2已报运
            contract.setState(2);
        }
        export.setCustomerContract(sb.toString());
        export.setProNum(exportPNum);
        export.setExtNum(exportENum);
        exportDao.insertSelective(export);
        //2.保存报运单下的货物
        ContractProductExample cpExample = new ContractProductExample();
        ContractProductExample.Criteria criteria = cpExample.createCriteria();
        criteria.andContractIdIn(Arrays.asList(contractIds));
        List<ContractProduct> contractProducts = contractProductDao.selectByExample(cpExample);
        //遍历货物
        for (ContractProduct contractProduct : contractProducts) {
            ExportProduct exportProduct = new ExportProduct();
            //复制
            BeanUtils.copyProperties(contractProduct, exportProduct);
            exportProduct.setExportId(export.getId());
            exportProduct.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            exportProduct.setCreateTime(new Date());
            exportProduct.setCreateBy(export.getCreateBy());
            exportProduct.setCreateDept(export.getCreateDept());
            exportProductDao.insertSelective(exportProduct);
            //3.保存报运单下的附件
            //根据货物ID查询货物下的附件
            ExtCproductExample extCproductExample = new ExtCproductExample();
            ExtCproductExample.Criteria extCproductExampleCriteria = extCproductExample.createCriteria();
            extCproductExampleCriteria.andContractProductIdEqualTo(contractProduct.getId());
            List<ExtCproduct> extCproducts = extCproductDao.selectByExample(extCproductExample);
            for (ExtCproduct extCproduct : extCproducts) {
                ExtEproduct extEproduct = new ExtEproduct();
                BeanUtils.copyProperties(extCproduct, extEproduct);
                extEproduct.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                extEproduct.setExportId(export.getId());
                extEproduct.setExportProductId(exportProduct.getId());
                extEproduct.setCreateTime(new Date());
                extEproduct.setCreateBy(export.getCreateBy());
                extEproduct.setCreateDept(export.getCreateDept());
                extEproductDao.insertSelective(extEproduct);
            }
        }

    }

    @Override
    public void update(Export export) {
        //修改报运单
        int i = exportDao.updateByPrimaryKeySelective(export);
        //循环修改报运单下的货物
        if (export.getExportProducts() != null && !export.getExportProducts().isEmpty()) {
            List<ExportProduct> exportProducts = export.getExportProducts();
            for (ExportProduct exportProduct : exportProducts) {
                int i1 = exportProductDao.updateByPrimaryKeySelective(exportProduct);
            }
        }
    }

    @Override
    public void delete(String id) {
        //查找报运单
        Export export = exportDao.selectByPrimaryKey(id);
        //修改报运单下的购销合同状态
        String[] split = export.getContractIds().split(",");
        ContractExample contractExample = new ContractExample();
        ContractExample.Criteria criteria = contractExample.createCriteria();
        criteria.andIdIn(Arrays.asList(split));
        List<Contract> contracts = contractDao.selectByExample(contractExample);
        for (Contract contract : contracts) {
            contract.setState(1);
        }
        //删除报运单货物和附件
        int i = exportProductDao.deleteByExportId(id);
        int i1 = extEproductDao.deleteByExportId(id);
        //删除报运单
        int i2 = exportDao.deleteByPrimaryKey(id);

    }

    @Override
    public PageInfo findAll(ExportExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<Export> exports = exportDao.selectByExample(example);
        PageInfo<Export> pageInfo = new PageInfo<>(exports);
        return pageInfo;
    }

    @Override
    public boolean exportE(String id) {
        //报运单和报运单下的货物转换
        Export export = exportDao.selectByPrimaryKey(id);
        ExportVo exportVo = new ExportVo();
        BeanUtils.copyProperties(export, exportVo);
        exportVo.setExportId(id);
        //货物转换
        ExportProductExample example = new ExportProductExample();
        example.createCriteria().andExportIdEqualTo(id);
        List<ExportProduct> exportProducts = exportProductDao.selectByExample(example);
        ArrayList<ExportProductVo> exportProductVos = new ArrayList<>();
        for (ExportProduct exportProduct : exportProducts) {
            ExportProductVo exportProductVo = new ExportProductVo();
            BeanUtils.copyProperties(exportProduct, exportProductVo);
            exportProductVo.setExportProductId(exportProduct.getId());
            exportProductVo.setExportId(id);
            exportProductVos.add(exportProductVo);
        }
        exportVo.setProducts(exportProductVos);
        //报运
        Response response = WebClient.create("http://localhost:9099/ws/export/user1")
                .type(MediaType.APPLICATION_XML_TYPE ).post(exportVo);
        int status = response.getStatus();
//        if (status == 200) {
//            ExportResult exportResult = WebClient.create("http://localhost:9099/ws/export/user/" + id)
//                    .type(MediaType.APPLICATION_XML_TYPE).get(ExportResult.class);
//            int i = updateByExportResult(exportResult);
//            if (i == 1) {
//                return true;
//            }
//        }
        return false;

    }

    private int updateByExportResult(ExportResult exportResult) {
        //更新报运单
        Export export = new Export();
        export.setId(exportResult.getExportId());
        export.setState(2);
        export.setRemark(exportResult.getRemark());
        int i = exportDao.updateByPrimaryKeySelective(export);
        //修改商品下的税
        Set<ExportProductResult> products = exportResult.getProducts();
        for (ExportProductResult product : products) {
            ExportProduct exportProduct = new ExportProduct();
            exportProduct.setId(product.getExportProductId());
            exportProduct.setTax(product.getTax());
            exportProductDao.updateByPrimaryKeySelective(exportProduct);
        }
        return 0;
    }
}
