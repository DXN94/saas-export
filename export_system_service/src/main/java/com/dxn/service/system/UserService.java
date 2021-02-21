package com.dxn.service.system;

import com.dxn.domain.system.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/24 17:09
 */
public interface UserService {

    /**
     * 查找所有
     * @param companyId
     * @return
     */
    List<User> findAll(String companyId);

    /**
     * 添加企业
     * @param User
     */
    void saveUser(User user);

    /**
     * shanchu
     * @param User
     */
    void deleteUser(User user);

    /**
     * gengxin
     * @param User
     */
    void updateUser(User user);

    /**
     * id
     * @param id
     * @return
     */
    User findById(String id);


    /**
     * 分页查找全部
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo findAll(String companyId,int page, int pageSize);

    /**
     * 更新用户角色
     * @param userId
     * @param roleIds
     */
    void updateUserRole(String userId, String roleIds);

    /**
     * 登陆用
     * @param email
     * @return
     */
    User findByEmail(String email);

}
