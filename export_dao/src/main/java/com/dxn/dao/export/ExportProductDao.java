package com.dxn.dao.export;

import com.dxn.domain.export.ExportProduct;
import com.dxn.domain.export.ExportProductExample;

import java.util.List;

public interface ExportProductDao {
	/**
	 * 根据id删除
	 */
    int deleteByPrimaryKey(String id);

	/**
	 * 保存
	 */
    int insertSelective(ExportProduct record);

	/**
	 * 条件查询
	 */
    List<ExportProduct> selectByExample(ExportProductExample example);

	/**
	 * 根据id查询
	 */
    ExportProduct selectByPrimaryKey(String id);

	/**
	 * 更新
	 */
    int updateByPrimaryKeySelective(ExportProduct record);

	/**
	 * 根据报运单id删除货物
	 * @param exportId
	 * @return
	 */
	int deleteByExportId(String exportId);
}