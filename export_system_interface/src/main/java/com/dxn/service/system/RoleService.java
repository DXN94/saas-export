package com.dxn.service.system;

import com.dxn.domain.system.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/24 17:09
 */
public interface RoleService {

    /**
     * 查找所有
     * @param companyId
     * @return
     */
    List<Role> findAll(String companyId);

    /**
     * 添加
     * @param role
     */
    void saveRole(Role role);

    /**
     * shanchu
     * @param Role
     */
    void deleteRole(Role role);

    /**
     * gengxin
     * @param Role
     */
    void updateRole(Role role);

    /**
     * id
     * @param id
     * @return
     */
    Role findById(String id);


    /**
     * 分页查找全部
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo findAll(String companyId,int page, int pageSize);

    /**
     * 查找该用户的角色
     * @param id
     * @return
     */
    List<Role> findUserRoleByUserId(String id);

    /**
     * 修改角色权限
     * @param roleid
     * @param moduleIds
     */
    void updateRoleModule(String roleid, String moduleIds);
}
