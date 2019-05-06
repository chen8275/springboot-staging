 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.service.impl;

 import com.example.demo.dao.UserRoleMapper;
 import com.example.demo.service.UserRoleService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.util.List;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 17:39
  */
 @Service
 public class UserRoleServiceImpl implements UserRoleService {
     @Autowired
     UserRoleMapper userRoleMapper;
     @Override
     public List<String> getRolesByUserId(String id) {
         return userRoleMapper.getRolesByUserId(id);
     }
 }
