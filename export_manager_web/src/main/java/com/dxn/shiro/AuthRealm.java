package com.dxn.shiro;

import com.dxn.domain.system.Module;
import com.dxn.domain.system.User;
import com.dxn.service.system.ModuleService;
import com.dxn.service.system.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/2/22 21:24
 */
public class AuthRealm extends AuthorizingRealm{

    @Resource
    private UserService userService;
    @Resource
    private ModuleService moduleService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1.获取安全数据
        //2.得到当前登录的用户对象
        User user = (User)principals.getPrimaryPrincipal();
        //3.通过用户对象获取用户的所有操作模块
        List<Module> moduleList = moduleService.findModulesByUser(user);
        //4.构造AuthorizationInfo对象返回
        Set<String> perms = new HashSet<>();

        for (Module module : moduleList) {
            perms.add(module.getName());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //将所有可操作模块的名称存入到授权对象中
        info.setStringPermissions(perms);
        return info;

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
