package com.dxn.service.system;

import com.dxn.domain.system.Module;
import com.dxn.domain.system.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/24 17:09
 */
public interface ModuleService {

    /**
     * 查找所有
     * @param
     * @return
     */
    List<Module> findAll();

    /**
     * 添加
     * @param Module
     */
    void saveModule(Module module);

    /**
     * shanchu
     * @param Module
     */
    void deleteModule(Module module);

    /**
     * gengxin
     * @param Module
     */
    void updateModule(Module module);

    /**
     * id
     * @param id
     * @return
     */
    Module findById(String id);


    /**
     * 分页查找全部
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo findAll(int page, int pageSize);

    /**
     * 根据角色id查出该角色所有权限
     * @param roleId
     * @return
     */
    List<String> findModulesByRoleId(String roleId);

    /**
     * 查找用户对应的模块
     * @param user
     * @return
     */
    List<Module> findModulesByUser(User user);

}
