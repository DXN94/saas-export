package com.dxn.dao.cargo;

import com.dxn.domain.cargo.ContractProduct;
import com.dxn.domain.cargo.ExtCproduct;
import com.dxn.domain.cargo.ExtCproductExample;

import java.util.List;

/**
 * @author dongxiangnan
 */
public interface ExtCproductDao {

	/**
	 * //删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * //保存
	 * @param record
	 * @return
	 */
	int insertSelective(ExtCproduct record);

	/**
	 * //条件查询
	 * @param example
	 * @return
	 */
	List<ExtCproduct> selectByExample(ExtCproductExample example);

	/**
	 * //id查询
	 * @param id
	 * @return
	 */
	ExtCproduct selectByPrimaryKey(String id);

	/**
	 * 更新
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(ExtCproduct record);

	/**
	 * 根据货物查询该货物所有附件
	 * @param id
	 * @return
	 */
	List<ContractProduct> findByProductId(String id);

}