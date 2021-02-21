package com.dxn.dao.company;

import com.dxn.domain.company.Company;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/21 20:05
 */
@Component
public interface CompanyDao {
    /**
     * 查找所有
     * @param company
     * @return
     */
    List<Company> findAll();

    /**
     * 添加企业
     * @param company
     */
    void saveCompany(Company company);

    /**
     * 删除企业
     * @param company
     */
    void deleteCompany(Company company);

    /**
     * 更新企业
     * @param company
     */
    void updateCompany(Company company);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    Company findById(String id);

    /**
     * 总数
     * @return
     */
    int count();

    /**
     * 分页查询
     * @param index
     * @param pageSize
     * @return
     */
    List findByLimit(@Param("index") int index, @Param("pageSize")int pageSize);
}
