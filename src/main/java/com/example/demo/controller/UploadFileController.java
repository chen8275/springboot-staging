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
 import com.example.demo.core.utils.UploadActionUtil;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;

 import javax.servlet.http.HttpServletRequest;
 import java.util.List;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 22:53
  */
 @RestController
 @RequestMapping("/uploadFile")
 public class UploadFileController {
     
     
     @PostMapping("/upload")
     public RetResult<List<String>> upload(HttpServletRequest httpServletRequest) throws Exception {
         List<String> list = UploadActionUtil.uploadFile(httpServletRequest);
         return RetResponse.makeOKRsp(list);
     }
     
 }
