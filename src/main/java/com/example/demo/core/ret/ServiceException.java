 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.ret;

 import java.io.Serializable;

 /**
  * @Description: 业务类异常
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 14:36
  */
 public class ServiceException extends RuntimeException implements Serializable {
     private static final long serialVersionUID = -5727596174564672286L;
     
     public ServiceException() {
     }
    
     public ServiceException(String message) {
         super(message);
     }
    
     public ServiceException(String message, Throwable cause) {
         super(message, cause);
     }
     
 }
