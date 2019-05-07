 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.tasks;

 import org.springframework.scheduling.annotation.EnableScheduling;
 import org.springframework.scheduling.annotation.Scheduled;
 import org.springframework.stereotype.Component;

 import java.util.Date;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/7 11:04
  * 具体cron表达式用法大家可以百度，这里列出几个常用的：
  * 每隔5秒执行一次：* /5 * * * * ?
  * 每隔1分钟执行一次：0 * /1 * * * ?
  * 每天23点执行一次：0 0 23 * * ?
  * 每天凌晨1点执行一次：0 0 1 * * ?
  * 每月1号凌晨1点执行一次：0 0 1 1 * ?
  * 每月最后一天23点执行一次：0 0 23 L * ?
  * 每周星期天凌晨1点实行一次：0 0 1 ? * L
  * 在26分、29分、33分执行一次：0 26,29,33 * * * ?
  * 每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?
  * 每隔5分钟执行一次：0 0/5 * * * ?
  */
 @Component
/**
 * 开启定时任务的注解
 */
 @EnableScheduling
 public class Task {
     @Scheduled(fixedRate = 5000)
     public void job1(){
         System.out.println("定时任务1" + new Date());
     }
    
     @Scheduled(cron = "0/5 * * * * ?")
     public void job2(){
         System.out.println("定时任务2" + new Date());
     }
    
     
 }
