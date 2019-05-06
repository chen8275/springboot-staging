 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.constant;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 15:44
  */
 public class ProjectConstant {
     
     // 项目基础包名称
     public static final String BASE_PACKAGE = "com.example.demo";
    
     // Model所在包
     public static final String MODEL_PACKAGE = BASE_PACKAGE + ".model";
    
     // Mapper所在包
     public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";
    
     // Service所在包
     public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";
    
     // ServiceImpl所在包
     public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";
    
     // Controller所在包
     public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";
    
     // Mapper插件基础接口的完全限定名
     public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.universal.Mapper";
    
     //文件上传储存的地址
     public static final String SAVEFILEPATH = "D://images";
     
 }
