package com.dxn.service.cargo;


import com.dxn.domain.cargo.ExtCproduct;
import com.dxn.domain.cargo.ExtCproductExample;
import com.github.pagehelper.PageInfo;

/**
 * @author dongxiangnan
 * 附件操作
 */
public interface ExtCproductService {
	/**
	 * 添加附件
	 * @param ExtCproduct
	 */
	void save(ExtCproduct ExtCproduct);

	/**
	 * 更新
	 */
	void update(ExtCproduct ExtCproduct);

	/**
	 * 删除
	 */
	void delete(String id);

	/**
	 * 根据id查询
	 */
	ExtCproduct findById(String id);

	/**
	 * 分页查询
	 */
	PageInfo findAll(ExtCproductExample example, int page, int size);
}
