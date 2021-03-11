package com.dxn.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dxn.dao.cargo.ContractDao;
import com.dxn.domain.cargo.Contract;
import com.dxn.domain.cargo.ContractExample;
import com.dxn.service.cargo.ContractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
        return 0;
    }

    @Override
    public Contract selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Contract record) {
        return 0;
    }

    @Override
    public PageInfo findAll(ContractExample contractExample,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Contract> contracts = contractDao.selectByExample(contractExample);
        return new PageInfo(contracts);
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
        return 0;
    }
}
