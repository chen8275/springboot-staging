 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.service.impl;

 import com.example.demo.model.SysPermissionInit;
 import com.example.demo.service.ShiroService;
 import com.example.demo.service.SysPermissionInitService;
 import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
 import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
 import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
 import org.apache.shiro.web.servlet.AbstractShiroFilter;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 22:28
  */
 @Service
 public class ShiroServiceImpl implements ShiroService {
     @Autowired
     ShiroFilterFactoryBean shiroFilterFactoryBean;
    
     @Autowired
     SysPermissionInitService sysPermissionInitService;
    
     /**
      * 初始化权限
      */
     @Override
     public Map<String, String> loadFilterChainDefinitions() {
         // 权限控制map.从数据库获取
         Map<String, String> filterChainDefinitionMap = new HashMap<>();
         List<SysPermissionInit> list = sysPermissionInitService.selectAllOrderBySort();
         for (SysPermissionInit sysPermissionInit : list) {
             filterChainDefinitionMap.put(sysPermissionInit.getUrl(),
                     sysPermissionInit.getPermissionInit());
         }
         return filterChainDefinitionMap;
     }
    
    
     /**
      * 重新加载权限
      */
     @Override
     public void updatePermission() {
         synchronized (shiroFilterFactoryBean) {
             AbstractShiroFilter shiroFilter = null;
             try {
                 shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
                         .getObject();
             } catch (Exception e) {
                 throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
             }
             PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                     .getFilterChainResolver();
             DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
                     .getFilterChainManager();
             // 清空老的权限控制
             manager.getFilterChains().clear();
             shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
             shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
             // 重新构建生成
             Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
             for (Map.Entry<String, String> entry : chains.entrySet()) {
                 String url = entry.getKey();
                 String chainDefinition = entry.getValue().trim().replace(" ", "");
                 manager.createChain(url, chainDefinition);
             }
             System.out.println("更新权限成功！！");
         }
     }
     
 }
