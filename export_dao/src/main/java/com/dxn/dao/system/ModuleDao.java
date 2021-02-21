package com.dxn.dao.system;

import com.dxn.domain.system.Module;
import java.util.List;

/**
 */
public interface ModuleDao {

    /**
     * 根据id查询
     * @param moduleId
     * @return
     */
    Module findById(String moduleId);

    /**
     * 根据id删除
     * @param moduleId
     * @return
     */
    int deleteModule(String moduleId);

    /**
     * 添加用户
     * @param module
     * @return
     */
    int saveModule(Module module);

    /**
     * 更新用户
     * @param module
     * @return
     */
    int updateModule(Module module);

    /**
     * 查询全部
     * @return
     */
    List<Module> findAll();

    /**
     * 根据角色id查出该角色所有权限
     * @param roleid
     * @return
     */
    List<String> findModulesByRoleId(String roleid);

    /**
     * 根据归属查模块
     * @param degree
     * @return
     */
    List<Module> findModulesByBelong(Integer degree);

    /**
     * 根据userId查询模块
     * @param id
     * @return
     */
    List<Module> findModulesByUserId(String id);
}