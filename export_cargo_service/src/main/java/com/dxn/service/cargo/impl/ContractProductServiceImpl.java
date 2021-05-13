package com.dxn.service.cargo.impl;

import com.dxn.dao.cargo.ContractDao;
import com.dxn.dao.cargo.ContractProductDao;
import com.dxn.dao.cargo.ExtCproductDao;
import com.dxn.domain.cargo.*;
import com.dxn.service.cargo.ContractProductService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * @author 29237
 */
@Service
public class ContractProductServiceImpl implements ContractProductService {

    @Autowired
    private ContractProductDao contractProductDao;

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private ExtCproductDao extCproductDao;

    /**
     * 1、保存货物
     * 2、更新合同
     * @param contractProduct
     */
    public void save(ContractProduct contractProduct) {
        // 1、设置货物id
        contractProduct.setId(UUID.randomUUID().toString().replace("-",""));
        // 2、求货物的总金额 = 单价* 数量
        double amount = 0.00;
        if(contractProduct.getPrice()!=null&&contractProduct.getCnumber()!=null){
            amount = contractProduct.getCnumber() * contractProduct.getPrice();
        }
        contractProduct.setAmount(amount);
        // 3、保存货物
        contractProductDao.insertSelective(contractProduct);

        // 4、根据货物中的合同id查询合同对象
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        // 5、更新合同总金额 = 原合同总金额 + 货物的总金额
        contract.setTotalAmount(contract.getTotalAmount() + amount);
        // 6、更新合同的货物数量 = 原货物数量 +1
        contract.setProNum(contract.getProNum() + 1);
        // 7、更新合同信息
        contractDao.updateByPrimaryKeySelective(contract);
    }

    /**
     * 1、修改货物
     * 2、修改合同
     * @param contractProduct
     */
    public void update(ContractProduct contractProduct) {
        //1、根据货物id查询原（数据库中的）货物对象
        ContractProduct oldCp = contractProductDao.selectByPrimaryKey(contractProduct.getId());
        //2、得到原货物总金额
        double oldAmount = oldCp.getAmount();
        //3、求新货物总金额
        double amount = 0.00;
        if(contractProduct.getPrice()!=null&&contractProduct.getCnumber()!=null){
            amount = contractProduct.getCnumber() * contractProduct.getPrice();
        }
        contractProduct.setAmount(amount);
        //4、根据货物中的合同id查询合同对象
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        //5、更新合同总金额 = 原合同总金额 - 原货物总金额 + 新货物总金额
        contract.setTotalAmount(contract.getTotalAmount() - oldAmount + amount);
        //6、更新货物对象
        contractProductDao.updateByPrimaryKeySelective(contractProduct);
        //7、更新合同对象
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void delete(String id) {
        // 1、根据货物id查询附件列表
        ExtCproductExample extCproductExample = new ExtCproductExample();
        ExtCproductExample.Criteria extCproductExampleCriteria = extCproductExample.createCriteria();
        extCproductExampleCriteria.andContractProductIdEqualTo(id);
        List<ExtCproduct> extCproducts = extCproductDao.selectByExample(extCproductExample);
        // 2、循环删除附件，同时累加附件总金额
        double extCpAmount = 0d;
        for (ExtCproduct extCproduct : extCproducts) {
            //累加附件总金额
            extCpAmount += extCproduct.getAmount();
            extCproductDao.deleteByPrimaryKey(extCproduct.getId());
        }
        // 3、根据货物id查询货物对象
        ContractProduct contractProduct = contractProductDao.selectByPrimaryKey(id);
        // 4、得到货物总金额
        double amount = contractProduct.getAmount();
        // 5、根据货物id删除货物
        contractProductDao.deleteByPrimaryKey(id);
        //
        // 6、根据货物中的合同id查询合同对象
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        // 7、修改货物数量
        contract.setProNum(contract.getProNum()-1);
        // 8、修改附件数量
        contract.setExtNum(contract.getExtNum()-extCproducts.size());
        // 9、修改总金额 - 货物总金额 - 附件总金额
        contract.setTotalAmount(contract.getTotalAmount() -amount - extCpAmount);
        // 10、更新合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public ContractProduct findById(String id) {
        return contractProductDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ContractProductExample example,int page,int size) {
        PageHelper.startPage(page, size);
        List<ContractProduct> list = contractProductDao.selectByExample(example);
        return new PageInfo(list);
    }

}
