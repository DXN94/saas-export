package com.dxn.service.system.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dxn.common.utils.UuidUtils;
import com.dxn.dao.system.DeptDao;
import com.dxn.domain.system.Dept;
import com.dxn.service.system.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

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
        //1.判断有没有上级部门
        if (dept.getParent().getId().isEmpty()){
            //没有上级部门，设置4位随机id
            dept.setId(UuidUtils.uuid4());
        } else {
            //如果有上级部门先查出上级部门id然后拼写本部门id
            dept.setId(dept.getParent().getId()+UuidUtils.uuid4());
        }
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
