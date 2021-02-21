package com.dxn.dao.system;

import com.dxn.domain.system.Dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/24 16:55
 */
@Component
public interface DeptDao {
    /**
     * 查找所有
     * @param Dept
     * @return
     */
    List<Dept> findAll(String companyId);

    /**
     * 添加部门
     * @param Dept
     */
    void saveDept(Dept dept);

    /**
     * 删除部门
     * @param Dept
     */
    void deleteDept(Dept dept);

    /**
     * 更新部门
     * @param Dept
     */
    void updateDept(Dept dept);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    Dept findById(String id);

    /**
     * 总数
     * @return
     */
    int count();

    /**
     * 分页查询
     * @param index
     * @param pageSize
     * @return
     */
    List findByLimit(@Param("index") int index, @Param("pageSize")int pageSize);
}
