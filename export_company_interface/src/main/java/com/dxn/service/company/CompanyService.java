package com.dxn.service.company;

import com.dxn.domain.company.Company;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/21 20:19
 */
public interface CompanyService {
    /**
     * 查找所有
     * @param
     * @return
     */
    List<Company> findAll();

    /**
     * 添加企业
     * @param company
     */
    void saveCompany(Company company);

    /**
     * shanchu
     * @param company
     */
    void deleteCompany(Company company);

    /**
     * gengxin
     * @param company
     */
    void updateCompany(Company company);

    /**
     * id
     * @param id
     * @return
     */
    Company findById(String id);


    /**
     * 分页查找全部
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo findAll(int page, int pageSize);
}
