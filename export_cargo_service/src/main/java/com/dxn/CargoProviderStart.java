package com.dxn;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/3/11 20:44
 */
public class CargoProviderStart {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
        context.start(); //启动服务
        System.in.read(); // 按任意键退出
    }
}
