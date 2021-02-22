package com.dxn.shiro;

import com.dxn.domain.system.User;
import com.dxn.service.system.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/2/22 21:24
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取token中的用户名
        UsernamePasswordToken upt = (UsernamePasswordToken)token;
        String username = upt.getUsername();
        //根据用户查库
        User dbUser = userService.findByEmail(username);
        if (null != dbUser){
            //第一个参数：安全数据（user对象）
            //第二个参数：密码（数据库密码）
            //第三个参数：当前调用realm域的名称（类名即可）
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(dbUser,dbUser.getPassword(),this.getName());
            return info;
        }
        return null;
    }
}
