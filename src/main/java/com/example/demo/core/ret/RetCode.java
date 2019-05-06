 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.ret;

 /**
  * @Description: 响应码枚举，参考HTTP状态码的语义
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 14:12
  */
 public enum  RetCode {
    
     /**
      * 成功
      */
     SUCCESS(200),
    
     /**
      * 失败
      */
     FAIL(400),
    
     /**
      * 未认证（签名错误）
      */
     UNAUTHORIZED(401),
     
     /** 未登录 */
     UNAUTHEN(4401),
    
     /** 未授权，拒绝访问 */
     UNAUTHZ(4403),
     
     /**
      * 接口不存在
      */
     NOT_FOUND(404),
    
     /**
      * 服务器内部错误
      */
     INTERNAL_SERVER_ERROR(500);
    
     public int code;
    
     RetCode(int code) {
         this.code = code;
     }
 }
