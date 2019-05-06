 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.service;

 import com.example.demo.core.universal.Service;
 import com.example.demo.model.SysPermissionInit;

 import java.util.List;

 /**
  * @Description: SysPermissionInitService接口
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 21:58
  */
 public interface SysPermissionInitService extends Service<SysPermissionInit> {
     List<SysPermissionInit> selectAllOrderBySort();
 }
