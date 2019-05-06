 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.service;

 import java.util.Map;

 /**
  * shiro 动态更新权限
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 22:27
  */
 public interface ShiroService {
     
     Map<String, String> loadFilterChainDefinitions();
    
     /**
      * 动态修改权限
      */
     void updatePermission();
 }
