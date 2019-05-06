 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.configuration;

 import org.springframework.boot.web.servlet.MultipartConfigFactory;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;

 import javax.servlet.MultipartConfigElement;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 22:52
  */
 @Configuration
 public class MultipartConfiguration {
     @Bean
     public MultipartConfigElement multipartConfigElement(){
         MultipartConfigFactory factory=new MultipartConfigFactory();
         factory.setMaxFileSize("10MB");
         factory.setMaxRequestSize("10MB");
         return factory.createMultipartConfig();
     }
     
 }
