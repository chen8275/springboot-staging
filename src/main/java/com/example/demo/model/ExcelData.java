 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.model;

 import java.io.Serializable;
 import java.util.List;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/7 11:32
  */
 public class ExcelData implements Serializable {
     private static final long serialVersionUID = -488695846853086363L;
     /**
      * 表头
      */
     private List<String> titles;
    
     /**
      * 数据
      */
     private List<List<Object>> rows;
    
     /**
      * 页签名称
      */
     private String name;
    
     public List<String> getTitles() {
         return titles;
     }
    
     public void setTitles(List<String> titles) {
         this.titles = titles;
     }
    
     public List<List<Object>> getRows() {
         return rows;
     }
    
     public void setRows(List<List<Object>> rows) {
         this.rows = rows;
     }
    
     public String getName() {
         return name;
     }
    
     public void setName(String name) {
         this.name = name;
    
     }
 }
