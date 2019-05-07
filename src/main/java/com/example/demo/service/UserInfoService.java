 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.service;


 import com.example.demo.core.universal.Service;
 import com.example.demo.model.UserInfo;
 import com.github.pagehelper.PageInfo;

 import java.util.List;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 10:04
  */

 public interface UserInfoService extends Service<UserInfo> {
     
     List<UserInfo> selectAlla(int pageNum, int pageSize);
 }
