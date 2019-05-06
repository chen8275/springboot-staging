 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.configuration;

 import com.alibaba.fastjson.serializer.SerializerFeature;
 import com.alibaba.fastjson.support.config.FastJsonConfig;
 import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.http.MediaType;
 import org.springframework.http.converter.HttpMessageConverter;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

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
     
 }
