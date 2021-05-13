package com.dxn.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dxn.dao.cargo.FactoryDao;
import com.dxn.domain.cargo.Factory;
import com.dxn.domain.cargo.FactoryExample;
import com.dxn.service.cargo.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/4/12 21:16
 */
@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private FactoryDao factoryDao;

    @Override
    public void save(Factory factory) {

    }

    @Override
    public void update(Factory factory) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Factory findById(String id) {
        return null;
    }

    @Override
    public List<Factory> findAll(FactoryExample example) {
        List<Factory> factories = factoryDao.selectByExample(example);
        return factories;
    }
}
