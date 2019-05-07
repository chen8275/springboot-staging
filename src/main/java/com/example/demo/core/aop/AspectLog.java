 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.aop;

 import com.alibaba.fastjson.JSON;
 import com.example.demo.core.ret.ServiceException;
 import com.example.demo.core.utils.ApplicationUtils;
 import com.example.demo.model.SystemLog;
 import com.example.demo.model.UserInfo;
 import com.example.demo.service.SystemLogService;
 import lombok.extern.slf4j.Slf4j;
 import org.apache.ibatis.javassist.*;
 import org.apache.ibatis.javassist.bytecode.CodeAttribute;
 import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
 import org.apache.ibatis.javassist.bytecode.MethodInfo;
 import org.apache.shiro.SecurityUtils;
 import org.aspectj.lang.JoinPoint;
 import org.aspectj.lang.annotation.*;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
 
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import java.lang.reflect.Method;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Map;



 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 19:57
  */
 @Aspect
 @Component
 @Slf4j
 public class AspectLog {
     @Autowired
     SystemLogService systemLogService;
    
     /**
      * 定义切点
      */
     @Pointcut("@annotation(com.example.demo.core.aop.AnnotationLog)")
     public void methodCachePointcut() {
     }
    
     @Before("methodCachePointcut()")
     public void doBefore(JoinPoint p) throws Exception{
         SystemLog systemLog = getSystemLogInit(p);
         systemLog.setLogType("LOGINFO");
         systemLogService.insert(systemLog);
     }
    
     /**
      * 调用后的异常处理
      * @param p
      * @param e
      */
     @AfterThrowing(pointcut = "methodCachePointcut()", throwing = "e")
     public void doAfterThrowing(JoinPoint p, Throwable e) throws Throwable {
         //业务异常不用记录
         if(!(e instanceof ServiceException)) {
             try {
                 SystemLog systemLog =getSystemLogInit(p);
                 systemLog.setLogType("LOGERROR");
                 systemLog.setExceptionCode(e.getClass().getName());
                 systemLog.setExceptionDetail(e.getMessage());
                 systemLogService.insert(systemLog);
             } catch (Exception ex) {
                 log.error("==异常通知异常==");
                 log.error("异常信息:{}", ex.getMessage());
             }
         }
     }
    
     private SystemLog getSystemLogInit(JoinPoint p){
         SystemLog systemLog = new SystemLog();
         try{
             //类名
             String targetClass = p.getTarget().getClass().toString();
             //请求的方法名
             String tartgetMethod = p.getSignature().getName();
             //获取类名  UserController
             String classType = p.getTarget().getClass().getName();
             Class<?> clazz = Class.forName(classType);
             String clazzName = clazz.getName();
             //请求参数名+参数值的map
             Map<String, Object> nameAndArgs = getFieldsName(this.getClass(), clazzName, tartgetMethod, p.getArgs());
             systemLog.setId(ApplicationUtils.getUUID());
             systemLog.setDescription(getMthodRemark(p));
             systemLog.setMethod(targetClass+"."+tartgetMethod);
             //大家可自行百度获取ip的方法
             systemLog.setRequestIp("192.168.1.104");
             systemLog.setParams(JSON.toJSONString(nameAndArgs));
             systemLog.setUserId(getUserId());
             systemLog.setCreateTime(new Date());
         }catch (Exception ex){
             log.error("==异常通知异常==");
             log.error("异常信息:{}", ex.getMessage());
         }
         return systemLog;
     }
    
     /**
      * 通过反射机制 获取被切参数名以及参数值
      *
      * @param cls
      * @param clazzName
      * @param methodName
      * @param args
      * @return
      * @throws NotFoundException
      */
     private Map<String, Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws NotFoundException {
         Map<String, Object> map = new HashMap<>();
         ClassPool pool = ClassPool.getDefault();
         ClassClassPath classPath = new ClassClassPath(cls);
         pool.insertClassPath(classPath);
         CtClass cc = pool.get(clazzName);
         CtMethod cm = cc.getDeclaredMethod(methodName);
         MethodInfo methodInfo = cm.getMethodInfo();
         CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
         LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
         int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
         for (int i = 0; i < cm.getParameterTypes().length; i++) {
             //HttpServletRequest 和HttpServletResponse 不做处理
             if(!(args[i] instanceof HttpServletRequest || args[i] instanceof HttpServletResponse)){
                 //paramNames即参数名
                 map.put(attr.variableName(i + pos), JSON.toJSONString(args[i]));
             }
         }
         return map;
     }
    
     /**
      * 获取方法的中文备注____用于记录用户的操作日志描述
      * @param joinPoint
      * @return
      * @throws Exception
      */
     private static String getMthodRemark(JoinPoint joinPoint) throws Exception {
         String targetName = joinPoint.getTarget().getClass().getName();
         String methodName = joinPoint.getSignature().getName();
         Object[] arguments = joinPoint.getArgs();
         Class targetClass = Class.forName(targetName);
         Method[] method = targetClass.getMethods();
         String methode = "";
         for (Method m : method) {
             if (m.getName().equals(methodName)) {
                 Class[] tmpCs = m.getParameterTypes();
                 if (tmpCs.length == arguments.length) {
                     AnnotationLog methodCache = m.getAnnotation(AnnotationLog.class);
                     if (methodCache != null) {
                         methode = methodCache.remark();
                     }
                     break;
                 }
             }
         }
         return methode;
     }
    
     private static String getUserId() {
         String userId = "";
         UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
         if(userInfo != null){
             userId = userInfo.getId();
         }
         return userId;
     }
    
     
 }
