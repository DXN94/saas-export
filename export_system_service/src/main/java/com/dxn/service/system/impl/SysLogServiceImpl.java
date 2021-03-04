package com.dxn.service.system.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dxn.dao.system.SysLogDao;
import com.dxn.domain.system.SysLog;
import com.dxn.service.system.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * @author 29237
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;


    /**
     * 根据企业id查询部门分页列表
     * @param companyId
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo findAll(String companyId, int page, int size) {
        PageHelper.startPage(page, size);
        List list = sysLogDao.findAll(companyId);
        return new PageInfo(list);
    }

    @Override
    public void save(SysLog sysLog) {
        sysLog.setId(UUID.randomUUID().toString());
        sysLogDao.save(sysLog);
    }


}
