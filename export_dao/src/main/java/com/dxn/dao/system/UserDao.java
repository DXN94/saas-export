package com.dxn.dao.system;

import com.dxn.domain.system.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/24 16:55
 */
@Component
public interface UserDao {
    /**
     * 查找所有
     * @param User
     * @return
     */
    List<User> findAll(String companyId);

    /**
     * 添加部门
     * @param User
     */
    void saveUser(User user);

    /**
     * 删除部门
     * @param User
     */
    void deleteUser(User user);

    /**
     * 更新部门
     * @param User
     */
    void updateUser(User user);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    User findById(String id);

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

    /**
     * 删除一个用户所有角色
     * @param userId
     */
    void deleteUserAllRoles(String userId);

    /**
     * 给用户添角色
     * @param userId
     * @param roleId
     */
    void saveUserRole(@Param("userId") String userId, @Param("roleId")String roleId);

    /**
     * 登陆用
     * @param email
     * @return
     */
    User findByEmail(String email);

}
