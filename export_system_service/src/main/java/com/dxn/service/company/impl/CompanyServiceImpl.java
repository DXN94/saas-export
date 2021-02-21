package com.dxn.service.company.impl;

import com.dxn.common.entity.PageResult;
import com.dxn.dao.company.CompanyDao;
import com.dxn.domain.company.Company;
import com.dxn.service.company.CompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/21 20:20
 */
@SuppressWarnings("all")
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    private final String TIMEFORMAT1 = "yyyyMMddHHmmss";

    /**
     * 查找所有
     *
     * @param company
     * @return
     */
    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    /**
     * 添加企业
     *
     * @param company
     */
    @Override
    public void saveCompany(Company company) {
        Date date = new Date();
        company.setId(new SimpleDateFormat(TIMEFORMAT1).format(date));
        company.setBalance(0.0);
        String expirationDate = "20991231235959";
        try {
            company.setExpirationDate(new SimpleDateFormat(TIMEFORMAT1).parse(expirationDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        company.setState(0);
        companyDao.saveCompany(company);


    }

    /**
     * 删除企业
     *
     * @param company
     */
    @Override
    public void deleteCompany(Company company) {
        companyDao.deleteCompany(company);
    }

    /**
     * 更新企业
     *
     * @param company
     */
    @Override
    public void updateCompany(Company company) {
        companyDao.updateCompany(company);
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public Company findById(String id) {
        Company company = companyDao.findById(id);
        return company;
    }

    /*
    public PageResult findAll(int page, int pageSize) {
        // 查找总数
        int totalCount = companyDao.count();
        // 查找list数据
        int index = (page - 1) * pageSize;
        List<Company> rows = companyDao.findByLimit(index, pageSize);
        PageResult pageResult = new PageResult(totalCount,rows,page,pageSize);
        return pageResult;
    }*/

    /**
     * 分页查找
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo findAll(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Company> rows = companyDao.findAll();
        PageInfo pageInfo = new PageInfo(rows);
        return pageInfo;
    }


}
