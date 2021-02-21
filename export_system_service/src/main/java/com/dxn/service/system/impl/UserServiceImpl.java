package com.dxn.service.system.impl;

import com.dxn.dao.system.UserDao;
import com.dxn.domain.system.User;
import com.dxn.service.system.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public PageInfo findAll(String companyId,int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<User> users = userDao.findAll(companyId);
        PageInfo pageInfo = new PageInfo(users);
        return pageInfo;
    }

    @Override
    public void updateUserRole(String userId, String roleIds) {
        //删除一个用户所有角色
        userDao.deleteUserAllRoles(userId);
        //截取roleIds
        String[] split = roleIds.split(",");
        for (String roleId : split) {
            userDao.saveUserRole(userId,roleId);
        }
    }

    @Override
    public User findByEmail(String email) {
        User user = userDao.findByEmail(email);
        return user;
    }


    @Override
    public void updateUser(User user) {
        userDao.deleteUser(user);
        userDao.saveUser(user);
    }

    @Override
    public List<User> findAll(String companyId) {
        return userDao.findAll(companyId);
    }

    @Override
    public void saveUser(User user) {
        user.setId(UUID.randomUUID().toString());
        userDao.saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

}
