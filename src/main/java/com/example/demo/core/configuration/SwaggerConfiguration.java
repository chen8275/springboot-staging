 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.configuration;

 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import springfox.documentation.builders.ApiInfoBuilder;
 import springfox.documentation.builders.PathSelectors;
 import springfox.documentation.builders.RequestHandlerSelectors;
 import springfox.documentation.service.ApiInfo;
 import springfox.documentation.spi.DocumentationType;
 import springfox.documentation.spring.web.plugins.Docket;
 import springfox.documentation.swagger2.annotations.EnableSwagger2;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 14:52
  */
 @Configuration
 @EnableSwagger2
 public class SwaggerConfiguration {
     @Bean
     public Docket createRestApi() {
         return new Docket(DocumentationType.SWAGGER_2)
                 .apiInfo(apiInfo())
                 .select()
                 .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                 .paths(PathSelectors.any())
                 .build();
     }
    
     private ApiInfo apiInfo() {
         return new ApiInfoBuilder()
                 .title("使用Swagger2构建RESTful APIs")
                 .description("")
                 .termsOfServiceUrl("")
                 .contact("")
                 .version("1.0")
                 .build();
     }
    
 }
