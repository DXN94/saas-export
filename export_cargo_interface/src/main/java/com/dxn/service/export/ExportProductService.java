package com.dxn.service.export;


import com.dxn.domain.export.ExportProduct;
import com.dxn.domain.export.ExportProductExample;

import java.util.List;


/**
 * 报运单下的货物
 * @author dongxiangnan
 */
public interface ExportProductService {

	ExportProduct findById(String id);

	void save(ExportProduct exportProduct);

	void update(ExportProduct exportProduct);

	void delete(String id);

	List<ExportProduct> findAll(ExportProductExample example);
}
