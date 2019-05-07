 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.configuration;

 import com.example.demo.core.shiro.CustomRealm;
 import com.example.demo.core.shiro.MySessionManager;
 import com.example.demo.model.SysPermissionInit;
 import com.example.demo.service.SysPermissionInitService;
 import org.apache.shiro.realm.Realm;
 import org.apache.shiro.session.mgt.SessionManager;
 import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
 import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
 import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;

 import java.util.List;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 17:43
  */
 @Configuration
 public class ShiroConfiguration {
     
     @Autowired
     SysPermissionInitService sysPermissionInitService;
     /**
      * 注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
      */
     @Bean
     public Realm realm() {
         return new CustomRealm();
     }
     
     @Bean
     public SessionManager sessionManager(){
         return new MySessionManager();
     }
     
     @Bean
     public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
         DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
         /**
          * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
          * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
          * 加入这项配置能解决这个bug
          */
         creator.setUsePrefix(true);
         return creator;
     }
    
     /**
      * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录
      * @return
      */
     @Bean
     public ShiroFilterChainDefinition shiroFilterChainDefinition() {
         DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
         List<SysPermissionInit> list = sysPermissionInitService.selectAllOrderBySort();
         for(int i = 0,length = list.size();i<length;i++){
             SysPermissionInit sysPermissionInit = list.get(i);
             chain.addPathDefinition(sysPermissionInit.getUrl(), sysPermissionInit.getPermissionInit());
         }
         return chain;
     }
    
     
     
     
 }
