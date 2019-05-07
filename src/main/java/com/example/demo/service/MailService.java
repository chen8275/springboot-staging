 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.service;

 import com.example.demo.model.Mail;

 import javax.servlet.http.HttpServletRequest;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/7 9:49
  */
 public interface MailService {
     /**
      * 发送简单邮件
      * @param mail
      */
     void sendSimpleMail(Mail mail);
    
     /**
      * 发送带附件的邮件
      * @param mail
      * @param request
      */
     void sendAttachmentsMail(Mail mail, HttpServletRequest request);
    
     /**
      * 发送静态资源  一张照片
      * @param mail
      * @throws Exception
      */
     void sendInlineMail(Mail mail) throws Exception;
    
     /**
      * 发送模板邮件
      * @param mail
      */
     void sendTemplateMail(Mail mail);
     
 }
