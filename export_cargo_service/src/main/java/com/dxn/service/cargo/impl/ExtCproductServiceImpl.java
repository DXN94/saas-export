package com.dxn.service.cargo.impl;

import com.dxn.dao.cargo.ContractDao;
import com.dxn.dao.cargo.ExtCproductDao;
import com.dxn.domain.cargo.Contract;
import com.dxn.domain.cargo.ContractProduct;
import com.dxn.domain.cargo.ExtCproduct;
import com.dxn.domain.cargo.ExtCproductExample;
import com.dxn.service.cargo.ExtCproductService;
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
public class ExtCproductServiceImpl implements ExtCproductService {

    @Autowired
    private ExtCproductDao extCproductDao;

    @Autowired
    private ContractDao contractDao;


    @Override
    public void save(ExtCproduct extCproduct) {
        extCproduct.setId(UUID.randomUUID().toString());
        //求附件总金额
        double amount = 0d;
        if(extCproduct.getCnumber()!=null&&extCproduct.getPrice()!=null){
            amount = extCproduct.getCnumber() * extCproduct.getPrice();
        }
        extCproduct.setAmount(amount);

        //1、保存附件
        extCproductDao.insertSelective(extCproduct);

        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        //修改合同的总金额
        contract.setTotalAmount(contract.getTotalAmount() + amount);
        //修改附件数量
        contract.setExtNum(contract.getExtNum() + 1);

        //2、修改合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void update(ExtCproduct extCproduct) {
        //1、根据货物id查询原（数据库中的）货物对象
        ExtCproduct oldCp = extCproductDao.selectByPrimaryKey(extCproduct.getId());
        //2、得到原货物总金额
        double oldAmount = oldCp.getAmount();
        //3、求新货物总金额
        double amount = 0d;
        if(extCproduct.getPrice()!=null&&extCproduct.getCnumber()!=null){
            amount = extCproduct.getCnumber() * extCproduct.getPrice();
        }
        extCproduct.setAmount(amount);
        //4、根据货物中的合同id查询合同对象
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        //5、更新合同总金额 = 原合同总金额 - 原货物总金额 + 新货物总金额
        contract.setTotalAmount(contract.getTotalAmount() - oldAmount + amount);
        //6、更新货物对象
        extCproductDao.updateByPrimaryKeySelective(extCproduct);
        //7、更新合同对象
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void delete(String id) {
        ExtCproduct extCproduct = extCproductDao.selectByPrimaryKey(id);
        //得到附件总金额
        double amount = extCproduct.getAmount();

        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        contract.setTotalAmount(contract.getTotalAmount() -amount);
        contract.setExtNum(contract.getExtNum() - 1);

        //删除附件
        extCproductDao.deleteByPrimaryKey(id);
        //更新合同
        contractDao.updateByPrimaryKeySelective(contract);

    }

    @Override
    public ExtCproduct findById(String id) {
        return extCproductDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ExtCproductExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<ExtCproduct> list = extCproductDao.selectByExample(example);
        return new PageInfo(list);
    }

}
