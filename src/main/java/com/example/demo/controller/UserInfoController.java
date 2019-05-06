 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.controller;

 import com.example.demo.core.ret.RetResponse;
 import com.example.demo.core.ret.RetResult;
 import com.example.demo.model.UserInfo;
 import com.example.demo.service.UserInfoService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;

 import java.util.List;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 10:05
  */
 @RestController
 @RequestMapping("userInfo")
 public class UserInfoController {
     
     @Autowired
     UserInfoService userInfoService;
     
     @GetMapping("/hello")
     public String hello(){
         return "hello SpringBoot";
     }
    
     @PostMapping("/selectById")
     public UserInfo selectById(Integer id){
         return userInfoService.selectById(id);
     }
     
     @PostMapping("/selectById2")
     public RetResult<UserInfo> selectById2(Integer id){
         UserInfo userInfo = userInfoService.selectById(id);
         return RetResponse.makeOKRsp(userInfo);
     }
    
     @PostMapping("/testException")
     public RetResult<UserInfo> testException(Integer id){
         List a = null;
         a.size();
         UserInfo userInfo = userInfoService.selectById(id);
         return RetResponse.makeOKRsp(userInfo);
     }
     
     
 }
