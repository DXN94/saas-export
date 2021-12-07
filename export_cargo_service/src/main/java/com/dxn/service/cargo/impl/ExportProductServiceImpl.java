package com.dxn.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dxn.domain.export.ExportProduct;
import com.dxn.domain.export.ExportProductExample;
import com.dxn.service.export.ExportProductService;

import java.util.List;

/**
 * @author dxn
 * @date 2021年12月06日9:58 下午
 */
@Service
public class ExportProductServiceImpl implements ExportProductService {
    @Override
    public ExportProduct findById(String id) {
        return null;
    }

    @Override
    public void save(ExportProduct exportProduct) {

    }

    @Override
    public void update(ExportProduct exportProduct) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<ExportProduct> findAll(ExportProductExample example) {
        return null;
    }
}
