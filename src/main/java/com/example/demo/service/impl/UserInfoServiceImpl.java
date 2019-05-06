 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.service.impl;

 import com.example.demo.core.ret.ServiceException;
 import com.example.demo.dao.UserInfoMapper;
 import com.example.demo.model.UserInfo;
 import com.example.demo.service.UserInfoService;
 import com.github.pagehelper.PageHelper;
 import com.github.pagehelper.PageInfo;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.util.List;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 10:05
  */
 @Service
 public class UserInfoServiceImpl implements UserInfoService {
     
     @Autowired
     UserInfoMapper userInfoMapper;
     
     public UserInfo selectById(Integer id){
         
         UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
         if (userInfo==null){
             throw new ServiceException("暂无该用户");
         }
         return userInfo;
     }
    
     @Override
     public PageInfo<UserInfo> selectAll(Integer page, Integer size) {
         //开启分页查询，写在查询语句上方
         //只有紧跟在PageHelper.startPage()方法后的第一个Mybatis的查询（Select）方法会被分页。
         PageHelper.startPage(page, size);
         List<UserInfo> userInfoList = userInfoMapper.selectAll();
         PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfoList);
         return pageInfo;
     }
     
 }
