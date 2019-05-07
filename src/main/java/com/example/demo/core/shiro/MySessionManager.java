 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.shiro;

 import org.apache.commons.lang3.StringUtils;
 import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
 import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
 import org.apache.shiro.web.util.WebUtils;

 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import java.io.Serializable;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/7 13:49
  */
 public class MySessionManager extends DefaultWebSessionManager {
     //请求头的名字
     private static final String AUTHORIZATION = "Authorization";
    
     private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";
    
     public MySessionManager() {
         super();
     }
    
     @Override
     protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
         String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
         //如果请求头中有 Authorization 则其值为sessionId
         if (!StringUtils.isEmpty(id)) {
             request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
             request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
             request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
             return id;
         } else {
             //否则按默认规则从cookie取sessionId
             return super.getSessionId(request, response);
         }
     }
 }

