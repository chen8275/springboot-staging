 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.service;


 import com.example.demo.model.UserInfo;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 10:04
  */

 public interface UserInfoService {
     
     UserInfo selectById(Integer id);
     
 }
