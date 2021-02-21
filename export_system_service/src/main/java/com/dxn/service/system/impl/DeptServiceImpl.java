package com.dxn.service.system.impl;

import com.dxn.dao.system.DeptDao;
import com.dxn.domain.system.Dept;
import com.dxn.service.system.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/24 17:11
 */
@Service
public class DeptServiceImpl implements DeptService {

    private final String TIMEFORMAT1 = "yyyyMMddHHmmss";

    @Autowired
    private DeptDao deptDao;

    @Override
    public List<Dept> findAll(String companyId) {
        return deptDao.findAll(companyId);
    }

    @Override
    public void saveDept(Dept dept) {
        dept.setId(new SimpleDateFormat(TIMEFORMAT1).format(new Date()));
        deptDao.saveDept(dept);
    }

    @Override
    public void deleteDept(Dept dept) {

        deptDao.deleteDept(dept);
    }

    @Override
    public void updateDept(Dept dept) {
        deptDao.updateDept(dept);
    }

    @Override
    public Dept findById(String id) {
        return deptDao.findById(id);
    }

    @Override
    public PageInfo findAll(String companyId,int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Dept> depts = deptDao.findAll(companyId);
        PageInfo pageInfo = new PageInfo(depts);
        return pageInfo;
    }
}
