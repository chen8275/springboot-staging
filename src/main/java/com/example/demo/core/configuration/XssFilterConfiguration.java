 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.configuration;

 import com.example.demo.core.filter.XssFilter;
 import org.springframework.boot.web.servlet.FilterRegistrationBean;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;

 import java.util.HashMap;
 import java.util.Map;

 /**
  * @Description:xss过滤拦截器配置文件
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 17:07
  */
 @Configuration
 public class XssFilterConfiguration {
     /**
      * xss过滤拦截器
      */
     @Bean
     public FilterRegistrationBean xssFilterRegistrationBean() {
         FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
         filterRegistrationBean.setFilter(new XssFilter());
         filterRegistrationBean.setOrder(Integer.MAX_VALUE-1);
         filterRegistrationBean.setEnabled(true);
         filterRegistrationBean.addUrlPatterns("/*");
         Map<String, String> initParameters = new HashMap();
         //excludes用于配置不需要参数过滤的请求url
         initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
         //isIncludeRichText主要用于设置富文本内容是否需要过滤
         initParameters.put("isIncludeRichText", "true");
         filterRegistrationBean.setInitParameters(initParameters);
         return filterRegistrationBean;
     }
     
 }
