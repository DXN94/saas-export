package com.dxn.dao.cargo;

import com.dxn.domain.cargo.Contract;
import com.dxn.domain.cargo.ContractExample;
import com.dxn.domain.vo.PrintContract;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 29237
 */
public interface ContractDao {
    int deleteByPrimaryKey(String id);

    int insert(Contract record);

    int insertSelective(Contract record);

    List<Contract> selectByExample(ContractExample example);

    Contract selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);

    List<Contract> findByCompanyId(String companyId);

    List<PrintContract> findPrintContract(@Param("inputDate") String inputDate, @Param("companyId") String companyId);
}