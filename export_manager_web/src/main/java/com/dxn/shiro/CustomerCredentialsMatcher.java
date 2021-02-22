package com.dxn.shiro;

import com.dxn.common.utils.Encrypt;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/2/22 21:48
 */
public class CustomerCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //获取用户登录填写的密码
        UsernamePasswordToken upt = (UsernamePasswordToken) token;
        String username = upt.getUsername();
        String loginPwd = new String(upt.getPassword());
        //获取数据库中查到的密码
        String dbPwd = (String) info.getCredentials();
        //对比密码
        String loginPwdMd5 = Encrypt.md5(loginPwd, username+"SeiDouJieBuKai");
        if (loginPwdMd5.equals(dbPwd)){
            return true;
        }
        return false;
    }
}
