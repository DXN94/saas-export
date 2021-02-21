package com.dxn.dao.system;

import com.dxn.domain.system.Role;
import org.apache.ibatis.annotations.Param;
import java.util.List;


public interface RoleDao {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Role findById(String id);

    /**
     * 查询全部用户
     * @param companyId
     * @return
     */
    List<Role> findAll(String companyId);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteRole(String id);

    /**
     * 添加
     * @param role
     * @return
     */
    int saveRole(Role role);

    /**
     * 更新
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 查找该用户的角色
     * @param id
     * @return
     */
    List<Role> findUserRoleByUserId(String id);

    /**
     * 先删除该角色所有权限
     * @param roleid
     */
    void deleteRoleModule(String roleid);

    /**
     * 添加权限
     * @param roleid
     * @param moduleId
     */
    void saveRoleModule(@Param("rolrid") String roleid, @Param("moduleId") String moduleId);
}