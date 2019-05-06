 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.utils;

 import org.apache.shiro.crypto.hash.SimpleHash;
 import org.apache.shiro.util.ByteSource;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 19:37
  */
 public class test {
     public static void main(String []ages ){
         //加密方式
         String hashAlgorithmName = "md5";
         //原密码
         String credentials = "1234";
         //加密次数
         int hashIterations = 1024;
         //加密盐值，大家可以用生成字符串的方法
         String hash = "wxKYXuTPST5SG0QzVPsg==";
         ByteSource credentialsSalt = ByteSource.Util.bytes(hash);
         String password = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations).toHex();
         System.out.println(password);
     }
     
 }
