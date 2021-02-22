package com.dxn;

import com.dxn.common.utils.Encrypt;
import org.junit.Test;

/**
 * @author dxn
 * @version 1.0
 * @date 2021/2/22 22:10
 */
public class Md5Test {
    @Test
    public void test1(){
        String pwd = "123456";
        String s = Encrypt.md5(pwd, "wudi@163.com" + "SeiDouJieBuKai");
        System.out.println(s);
    }
}
