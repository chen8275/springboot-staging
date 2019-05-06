 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.ret;

 /**
  * @Description: 将结果转换为封装后的对象
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 14:18
  */
 public class RetResponse {
     
     
     private final static String SUCCESS = "success";
    
     public static <T> RetResult<T> makeOKRsp() {
         return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS);
     }
    
     public static <T> RetResult<T> makeOKRsp(T data) {
         return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS).setData(data);
     }
    
     public static <T> RetResult<T> makeErrRsp(String message) {
         return new RetResult<T>().setCode(RetCode.FAIL).setMsg(SUCCESS);
     }
    
     public static <T> RetResult<T> makeRsp(int code, String msg) {
         return new RetResult<T>().setCode(code).setMsg(msg);
     }
    
     public static <T> RetResult<T> makeRsp(int code, String msg, T data) {
         return new RetResult<T>().setCode(code).setMsg(msg).setData(data);
     }
     
 }
