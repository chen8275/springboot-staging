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
 import com.github.pagehelper.PageInfo;
 import io.swagger.annotations.Api;
 import io.swagger.annotations.ApiImplicitParam;
 import io.swagger.annotations.ApiImplicitParams;
 import io.swagger.annotations.ApiOperation;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.*;

 import java.util.List;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 10:05
  */
 @RestController
 @RequestMapping("userInfo")
 @Api(tags = {"用户操作接口"}, description = "userInfoControler")
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
     @ApiOperation(value = "查询用户", notes = "根据用户ID查询用户")
     @ApiImplicitParams({
             @ApiImplicitParam(name = "id", value = "用户ID", required = true,
                     dataType = "Integer", paramType = "query")
     })
     public RetResult<UserInfo> selectById2(@RequestParam Integer id){
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
    
     @ApiOperation(value = "查询用户", notes = "分页查询用户所有")
     @ApiImplicitParams({
             @ApiImplicitParam(name = "page", value = "当前页码",
                     dataType = "Integer", paramType = "query"),
             @ApiImplicitParam(name = "size", value = "每页显示条数",
                     dataType = "Integer", paramType = "query")
     })
     @PostMapping("/selectAll")
     public RetResult<PageInfo<UserInfo>> selectAll(@RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "0") Integer size) {
         PageInfo<UserInfo> pageInfo = userInfoService.selectAll(page, size);
         return RetResponse.makeOKRsp(pageInfo);
     }
     
 }
