 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.startuprunner;

 import lombok.extern.slf4j.Slf4j;
 import org.springframework.boot.ApplicationArguments;
 import org.springframework.boot.ApplicationRunner;
 import org.springframework.core.annotation.Order;
 import org.springframework.stereotype.Component;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/7 10:25
  */
 @Component
 @Order(value = 1)
 @Slf4j
 public class StartupRunner1 implements ApplicationRunner {
     @Override
     public void run(ApplicationArguments var1) throws Exception{
         log.info("服务器启动成功！<<<<使用ApplicationRunner接口");
     }
     
 }
