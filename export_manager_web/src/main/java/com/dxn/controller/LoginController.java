package com.dxn.controller;

import com.dxn.domain.system.Module;
import com.dxn.domain.system.User;
import com.dxn.service.system.ModuleService;
import com.dxn.service.system.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/1/22 10:20
 */

@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;


    @RequestMapping(value = "/login",name = "登录")
    public String login(String email,String password){

        //查看当前session中有没有用户登录
        User loginUser = (User)session.getAttribute("user");
        if (null != loginUser){
            List<Module> modules = moduleService.findModulesByUser(loginUser);
            session.setAttribute("modules",modules);
            session.setAttribute("user",loginUser);
            return "/home/main";
        }
        //判断是否有用户名或者密码
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)){
            return "redirect:/login.jsp";
        }
        try {
            //1.获取subject
            Subject subject = SecurityUtils.getSubject();
            //2.获取token
            UsernamePasswordToken uP = new UsernamePasswordToken(email,password);
            //3.激活SecurityManager
            subject.login(uP);
            //4.获取安全对象
            User user = (User)subject.getPrincipal();
            //5.加到session中
            session.setAttribute("user",user);
            // 查找用户对应的权限
            List<Module> modules = moduleService.findModulesByUser(user);
            session.setAttribute("modules",modules);
            return "/home/main";
        } catch (AuthenticationException e) {
            //发生异常,跳转到错误页面
            request.setAttribute("error","用户名或密码错误");
            return "forward:/unauthorized.jsp";
        }
    }

    /*@RequestMapping(value = "/login",name = "登录")
    public String login(String email,String password){

        //查看当前session中有没有用户登录
        User loginUser = (User)session.getAttribute("user");
        if (null != loginUser){
            List<Module> modules = moduleService.findModulesByUser(loginUser);
            session.setAttribute("modules",modules);
            session.setAttribute("user",loginUser);
            return "/home/main";
        }
        //判断是否有用户名或者密码
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)){
            return "forward:/login.jsp";
        }
        //判断用户
        User user = userService.findByEmail(email);
        if (null != user){
            //判断密码是否正确
            if (!user.getPassword().equals(password)){
                request.setAttribute("error","PASSWORD IS FILED !!!");
                return "forward:/login.jsp";
            }
            //密码正确
            List<Module> modules = moduleService.findModulesByUser(user);
            session.setAttribute("modules",modules);
            session.setAttribute("user",user);
            return "/home/main";
        }
        request.setAttribute("error","USER IS EMPTY !!!");
        return "forward:/login.jsp";
    }*/
    @RequestMapping("/logout")
    public String login(){
        session.removeAttribute("user");
        return "forward:/login.jsp";
    }

}
