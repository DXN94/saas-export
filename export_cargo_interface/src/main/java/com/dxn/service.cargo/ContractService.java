package com.dxn.service.cargo;

import com.dxn.domain.cargo.Contract;
import com.dxn.domain.cargo.ContractExample;
import com.dxn.domain.vo.PrintContract;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/3/11 20:21
 */
public interface ContractService {

    int deleteByPrimaryKey(String id);

    int insert(Contract record);

    int insertSelective(Contract record);

    List<Contract> selectByExample(ContractExample example);

    Contract selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);

    /**
     * @param contractExample
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo findAll(ContractExample contractExample, int pageNum, int pageSize);

    /**
     * 根据id修改合同状态
     * @param id
     * @param state
     * @return
     */
    int updateStatusById(String id, int state);

    /**
     * 查询出货表用于打印
     * @param inputDate
     * @param companyId
     * @return
     */
    List<PrintContract> findPrintContract(String inputDate, String companyId);
}
