package com.dxn.service.system.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dxn.dao.system.RoleDao;
import com.dxn.domain.system.Role;
import com.dxn.service.system.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * @author 29237
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public List<Role> findAll(String companyId) {
        List<Role> roles = roleDao.findAll(companyId);
        return roles;
    }

    @Override
    public void saveRole(Role role) {
        role.setId(UUID.randomUUID().toString());
        roleDao.saveRole(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleDao.deleteRole(role.getId());
    }

    @Override
    public void updateRole(Role role) {
        roleDao.deleteRole(role.getId());
        roleDao.saveRole(role);

    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public PageInfo findAll(String companyId, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Role> roles = roleDao.findAll(companyId);
        PageInfo pageInfo = new PageInfo(roles);
        return pageInfo;
    }

    @Override
    public List<Role> findUserRoleByUserId(String id) {
        List<Role> roles = roleDao.findUserRoleByUserId(id);
        return roles;
    }

    @Override
    public void updateRoleModule(String roleid, String moduleIds) {
        //先删除该角色所有权限
        roleDao.deleteRoleModule(roleid);
        //分割moduleId
        String[] strings = moduleIds.split(",");
        for (String string : strings) {
            //添加权限
            roleDao.saveRoleModule(roleid,string);
        }
    }


}
