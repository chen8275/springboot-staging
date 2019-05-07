 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.controller;

 import com.example.demo.core.constant.ExcelConstant;
 import com.example.demo.core.ret.RetResponse;
 import com.example.demo.core.ret.RetResult;
 import com.example.demo.core.utils.ExcelUtils;
 import com.example.demo.model.ExcelData;
 import com.example.demo.model.UserInfo;
 import com.example.demo.service.UserInfoService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;

 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletResponse;
 import java.util.ArrayList;
 import java.util.List;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/7 11:35
  */
 @RestController
 @RequestMapping("excel")
 public class ExcelController {
     @Autowired
     private UserInfoService userInfoService;
    
     @PostMapping("/test")
     public RetResult<Integer> test(){
         int rowIndex = 0;
         List<UserInfo> list = userInfoService.selectAlla(0, 0);
         ExcelData data = new ExcelData();
         data.setName("hello");
         List<String> titles = new ArrayList();
         titles.add("ID");
         titles.add("userName");
         titles.add("password");
         data.setTitles(titles);
        
         List<List<Object>> rows = new ArrayList();
         for(int i = 0, length = list.size();i<length;i++){
             UserInfo userInfo = list.get(i);
             List<Object> row = new ArrayList();
             row.add(userInfo.getId());
             row.add(userInfo.getUserName());
             row.add(userInfo.getPassword());
             rows.add(row);
         }
         data.setRows(rows);
         try{
             rowIndex = ExcelUtils.generateExcel(data, ExcelConstant.FILE_PATH + ExcelConstant.FILE_NAME);
         }catch (Exception e){
             e.printStackTrace();
         }
         return RetResponse.makeOKRsp(Integer.valueOf(rowIndex));
     }
    
     @GetMapping("/test2")
     public void test2(HttpServletResponse response){
         int rowIndex = 0;
         List<UserInfo> list = userInfoService.selectAlla(0, 0);
         ExcelData data = new ExcelData();
         data.setName("hello");
         List<String> titles = new ArrayList();
         titles.add("ID");
         titles.add("userName");
         titles.add("password");
         data.setTitles(titles);
        
         List<List<Object>> rows = new ArrayList();
         for(int i = 0, length = list.size();i<length;i++){
             UserInfo userInfo = list.get(i);
             List<Object> row = new ArrayList();
             row.add(userInfo.getId());
             row.add(userInfo.getUserName());
             row.add(userInfo.getPassword());
             rows.add(row);
         }
         data.setRows(rows);
         try{
             ExcelUtils.exportExcel(response,"test2",data);
         }catch (Exception e){
             e.printStackTrace();
         }
     }
    
     
 }
