package com.dxn.dao.system;

import com.dxn.domain.system.SysLog;
import java.util.List;

/**
 * @author 29237
 */
public interface SysLogDao {
    /**
     * 查询全部
     * @param companyId
     * @return
     */
    List<SysLog> findAll(String companyId);

    /**
     * 添加日志
     * @param log
     * @return
     */
    int save(SysLog log);
}