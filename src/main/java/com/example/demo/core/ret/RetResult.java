 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.ret;

 import lombok.Data;

 /**
  * @Description 返回对象实体
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 14:14
  */

 public class RetResult<T> {
     
     public int code;
    
     private String msg;
    
     private T data;
     
     public RetResult<T> setCode(RetCode retCode) {
         this.code = retCode.code;
         return this;
     }
    
     public int getCode() {
         return code;
     }
    
     public RetResult<T> setCode(int code) {
         this.code = code;
         return this;
     }
    
     public String getMsg() {
         return msg;
     }
    
     public RetResult<T> setMsg(String msg) {
         this.msg = msg;
         return this;
     }
    
     public T getData() {
         return data;
     }
    
     public RetResult<T> setData(T data) {
         this.data = data;
         return this;
     }
     
 }
