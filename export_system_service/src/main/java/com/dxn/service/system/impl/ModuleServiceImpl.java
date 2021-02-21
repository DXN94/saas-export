package com.dxn.service.system.impl;

import com.dxn.dao.system.ModuleDao;
import com.dxn.domain.system.Module;
import com.dxn.domain.system.User;
import com.dxn.service.system.ModuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author 29237
 */
@Service
public class ModuleServiceImpl implements ModuleService {

    private static final int DEGREE0 = 0;
    private static final int DEGREE1 = 1;

    @Autowired
    private ModuleDao moduleDao;


    @Override
    public List<Module> findAll() {
        List<Module> modules = moduleDao.findAll();
        return modules;
    }

    @Override
    public void saveModule(Module module) {

        module.setId(UUID.randomUUID().toString());
        moduleDao.saveModule(module);
    }

    @Override
    public void deleteModule(Module module) {

        moduleDao.deleteModule(module.getId());
    }

    @Override
    public void updateModule(Module module) {

        moduleDao.deleteModule(module.getId());
        moduleDao.saveModule(module);
    }

    @Override
    public Module findById(String id) {
        return moduleDao.findById(id);
    }

    @Override
    public PageInfo findAll(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Module> modules = moduleDao.findAll();
        PageInfo pageInfo = new PageInfo(modules);
        return pageInfo;
    }

    @Override
    public List<String> findModulesByRoleId(String roleId) {
        List<String> modules = moduleDao.findModulesByRoleId(roleId);
        return modules;
    }

    @Override
    public List<Module> findModulesByUser(User user) {
        //判断用户的degree
        if (DEGREE0 == user.getDegree()){
            List<Module> modules = moduleDao.findModulesByBelong(user.getDegree());
            return modules;
        }else if (DEGREE1 == user.getDegree()){
            List<Module> modules = moduleDao.findModulesByBelong(user.getDegree());
            return modules;
        }else{
            return moduleDao.findModulesByUserId(user.getId());
        }
    }
}
