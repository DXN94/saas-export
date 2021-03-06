package com.dxn.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dxn.dao.cargo.ContractDao;
import com.dxn.domain.cargo.Contract;
import com.dxn.domain.cargo.ContractExample;
import com.dxn.domain.vo.PrintContract;
import com.dxn.service.cargo.ContractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/3/11 20:23
 */
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDao contractDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        int i = contractDao.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public Contract selectByPrimaryKey(String id) {
        //根据id查询合同
        Contract contract = contractDao.selectByPrimaryKey(id);
        return contract;
    }

    @Override
    public int updateByPrimaryKey(Contract record) {
        //根据id查出本合同创建时间
        Contract dbContract = contractDao.selectByPrimaryKey(record.getId());
        record.setCreateTime(dbContract.getCreateTime());
        record.setUpdateTime(new Date());
        record.setCompanyId(dbContract.getCompanyId());
        record.setOutState(dbContract.getOutState());
        record.setState(dbContract.getState());
        //总金额
        record.setTotalAmount(dbContract.getTotalAmount());
        //货物数
        record.setProNum(dbContract.getProNum());
        //附件数
        record.setExtNum(dbContract.getExtNum());
        int i = contractDao.updateByPrimaryKey(record);
        return i;
    }

    @Override
    public PageInfo findAll(ContractExample contractExample, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Contract> contracts = contractDao.selectByExample(contractExample);
        for (Contract contract : contracts) {
            System.out.println(contract.toString());
        }
        return new PageInfo(contracts);
    }

    @Override
    public int updateStatusById(String id, int state) {
        // 动态修改创建对象
        Contract contract = new Contract();
        contract.setState(state);
        contract.setId(id);
        int i = contractDao.updateByPrimaryKeySelective(contract);
        return i;
    }

    @Override
    public List<PrintContract> findPrintContract(String inputDate, String companyId) {

        return contractDao.findPrintContract(inputDate,companyId);
    }

    @Override
    public PageInfo<Contract> selectConEqSt(int page, int pageSize, String companyId) {
        PageHelper.startPage(page,pageSize);
        ContractExample example = new ContractExample();
        ContractExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        criteria.andStateEqualTo(1);
        List<Contract> contracts = contractDao.selectByExample(example);
        PageInfo<Contract> pageInfo = new PageInfo<>(contracts);
        return pageInfo;
    }

    @Override
    public int updateByPrimaryKeySelective(Contract record) {
        return 0;
    }

    @Override
    public List<Contract> selectByExample(ContractExample example) {
        return null;
    }

    @Override
    public int insertSelective(Contract record) {
        return 0;
    }

    @Override
    public int insert(Contract record) {
        record.setId(UUID.randomUUID().toString());
        record.setOutState(0);
        record.setState(0);
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        //设置总金额和数量
        //总金额
        record.setTotalAmount(0.00);
        //货物数
        record.setProNum(0);
        //附件数
        record.setExtNum(0);
        int insert = contractDao.insert(record);
        return insert;
    }
}
