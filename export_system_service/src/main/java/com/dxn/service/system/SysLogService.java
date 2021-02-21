package com.dxn.service.system;

import com.dxn.domain.system.SysLog;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 29237
 */
public interface SysLogService {

    /**
     * 根据企业id查询部门分页列表
     * @param companyId
     * @param page
     * @param size
     * @return
     */
    PageInfo findAll(String companyId, int page, int size);


    /**
     * 保存
     * @param sysLog
     */
    void save(SysLog sysLog);
}
