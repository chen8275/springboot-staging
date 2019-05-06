 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.service.impl;

 import com.example.demo.core.universal.AbstractService;
 import com.example.demo.dao.SystemLogMapper;
 import com.example.demo.model.SystemLog;
 import com.example.demo.service.SystemLogService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.util.List;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 17:15
  */
 @Service
 public class SystemLogServiceImpl extends AbstractService<SystemLog> implements SystemLogService {
     
     @Autowired
     SystemLogMapper systemLogMapper;
     
     @Override
     public Integer insertByBatch(List<SystemLog> list) {
         return systemLogMapper.insertByBatch(list);
     }
 }
