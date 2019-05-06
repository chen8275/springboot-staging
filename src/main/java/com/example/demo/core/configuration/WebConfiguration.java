 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.configuration;

 import com.alibaba.fastjson.JSON;
 import com.alibaba.fastjson.serializer.SerializerFeature;
 import com.alibaba.fastjson.support.config.FastJsonConfig;
 import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
 import com.example.demo.core.ret.RetCode;
 import com.example.demo.core.ret.RetResult;
 import com.example.demo.core.ret.ServiceException;
 import lombok.extern.slf4j.Slf4j;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.http.MediaType;
 import org.springframework.http.converter.HttpMessageConverter;
 import org.springframework.web.method.HandlerMethod;
 import org.springframework.web.servlet.HandlerExceptionResolver;
 import org.springframework.web.servlet.ModelAndView;
 import org.springframework.web.servlet.NoHandlerFoundException;
 import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import java.io.IOException;
 import java.nio.charset.Charset;
 import java.util.ArrayList;
 import java.util.List;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 14:29
  * 
  * @Description:
  * WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
  * WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
  * DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
  * WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
  * WriteMapNullValue：是否输出值为null的字段,默认为false
  */
 @Configuration
 @Slf4j
 public class WebConfiguration extends WebMvcConfigurationSupport {
     /**
      * 修改自定义消息转换器
      * @param converters
      */
     @Override
     public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
         FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
         converter.setSupportedMediaTypes(getSupportedMediaTypes());
         FastJsonConfig config = new FastJsonConfig();
         config.setSerializerFeatures(
                 // String null -> ""
                 SerializerFeature.WriteNullStringAsEmpty,
                 // Number null -> 0
                 SerializerFeature.WriteNullNumberAsZero,
                 //禁止循环引用
                 SerializerFeature.DisableCircularReferenceDetect
         );
         converter.setFastJsonConfig(config);
         converter.setDefaultCharset(Charset.forName("UTF-8"));
         converters.add(converter);
     }
    
     private List<MediaType> getSupportedMediaTypes() {
         List<MediaType> supportedMediaTypes = new ArrayList<>();
         supportedMediaTypes.add(MediaType.APPLICATION_JSON);
         supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
         supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
         supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
         supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
         supportedMediaTypes.add(MediaType.APPLICATION_PDF);
         supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
         supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
         supportedMediaTypes.add(MediaType.APPLICATION_XML);
         supportedMediaTypes.add(MediaType.IMAGE_GIF);
         supportedMediaTypes.add(MediaType.IMAGE_JPEG);
         supportedMediaTypes.add(MediaType.IMAGE_PNG);
         supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
         supportedMediaTypes.add(MediaType.TEXT_HTML);
         supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
         supportedMediaTypes.add(MediaType.TEXT_PLAIN);
         supportedMediaTypes.add(MediaType.TEXT_XML);
         return supportedMediaTypes;
     }
    
    
     @Override
     public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
         exceptionResolvers.add(getHandlerExceptionResolver());
     }
    
     /**
      * 创建异常处理
      * @return
      */
     private HandlerExceptionResolver getHandlerExceptionResolver(){
         HandlerExceptionResolver handlerExceptionResolver = new HandlerExceptionResolver(){
             @Override
             public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                                  Object handler, Exception e) {
                 RetResult<Object> result = getResultByHandleException(request, handler, e);
                 responseResult(response, result);
                 return new ModelAndView();
             }
         };
         return handlerExceptionResolver;
     }
    
     /**
      * 根据异常类型确定返回数据
      * @param request
      * @param handler
      * @param e
      * @return
      */
     private RetResult<Object> getResultByHandleException(HttpServletRequest request, Object handler, Exception e){
         RetResult<Object> result = new RetResult<>();
         if (e instanceof ServiceException) {
             result.setCode(RetCode.FAIL).setMsg(e.getMessage()).setData(null);
             return result;
         }
         if (e instanceof NoHandlerFoundException) {
             result.setCode(RetCode.NOT_FOUND).setMsg("接口 [" + request.getRequestURI() + "] 不存在");
             return result;
         }
         result.setCode(RetCode.INTERNAL_SERVER_ERROR).setMsg("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
         String message;
         if (handler instanceof HandlerMethod) {
             HandlerMethod handlerMethod = (HandlerMethod) handler;
             message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s", request.getRequestURI(),
                     handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod() .getName(), e.getMessage());
         } else {
             message = e.getMessage();
         }
         log.error(message, e);
         return result;
     }
    
     /**
      * @Title: responseResult
      * @Description: 响应结果
      * @param response
      * @param result
      * @Reutrn void
      */
     private void responseResult(HttpServletResponse response, RetResult<Object> result) {
         response.setCharacterEncoding("UTF-8");
         response.setHeader("Content-type", "application/json;charset=UTF-8");
         response.setStatus(200);
         try {
             response.getWriter().write(JSON.toJSONString(result,SerializerFeature.WriteMapNullValue));
         } catch (IOException ex) {
             log.error(ex.getMessage());
         }
     }
    
    
     /**
      * swagger2
      * @param registry
      */
     @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("swagger-ui.html")
                 .addResourceLocations("classpath:/META-INF/resources/");
         registry.addResourceHandler("/webjars/**")
                 .addResourceLocations("classpath:/META-INF/resources/webjars/");
         registry.addResourceHandler("/favicon.ico")
                 .addResourceLocations("classpath:/META-INF/resources/favicon.ico");
         super.addResourceHandlers(registry);
     }
     
 }
