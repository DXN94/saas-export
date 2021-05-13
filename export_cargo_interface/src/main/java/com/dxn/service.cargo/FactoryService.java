package com.dxn.service.cargo;

import com.dxn.domain.cargo.Factory;
import com.dxn.domain.cargo.FactoryExample;

import java.util.List;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/4/12 21:10
 */
public interface FactoryService {
    /**
     * 保存
     * @param factory
     */
    void save(Factory factory);

    /**
     * 更新
     * @param factory
     */
    void update(Factory factory);

    /**
     * 删除
     * @param id
     */
    void delete(String id);

    /**
     * 根据id查询
     */
    Factory findById(String id);

    /**
     * 查询所有
     * @param example
     * @return
     */
    List<Factory> findAll(FactoryExample example);

}
