package com.dxn.service.export;

import com.dxn.domain.export.Export;
import com.dxn.domain.export.ExportExample;
import com.github.pagehelper.PageInfo;


/**
 * 报运单管理
 * @author dongxiangnan
 */
public interface ExportService {

    Export findById(String id);

    void save(Export export);

    void update(Export export);

    void delete(String id);

	PageInfo findAll(ExportExample example, int page, int size);
}
