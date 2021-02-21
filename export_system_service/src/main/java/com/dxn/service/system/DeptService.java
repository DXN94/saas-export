package com.dxn.service.system;

import com.dxn.domain.system.Dept;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/24 17:09
 */
public interface DeptService {
    /**
     * 查找所有
     * @param dept
     * @return
     */
    List<Dept> findAll(String companyId);

    /**
     * 添加企业
     * @param dept
     */
    void saveDept(Dept dept);

    /**
     * shanchu
     * @param dept
     */
    void deleteDept(Dept dept);

    /**
     * gengxin
     * @param dept
     */
    void updateDept(Dept dept);

    /**
     * id
     * @param id
     * @return
     */
    Dept findById(String id);


    /**
     * 分页查找全部
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo findAll(String companyId,int page, int pageSize);
}
