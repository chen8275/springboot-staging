 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.configuration;

 import org.apache.ibatis.session.SqlSessionFactory;
 import org.mybatis.spring.SqlSessionFactoryBean;
 import org.mybatis.spring.mapper.MapperScannerConfigurer;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
 import org.springframework.core.io.support.ResourcePatternResolver;

 import javax.sql.DataSource;

 /**
  * @ClassName: MybatisConfiguration
  * @Description: Mybatis配置
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 10:07
  */
 @Configuration
 public class MybatisConfiguration {
     @Bean
     public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
         SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
         factory.setDataSource(dataSource);
         factory.setTypeAliasesPackage("com.example.demo.model");
         // 添加XML目录
         ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
         factory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
         return factory.getObject();
     }
    
     @Bean
     public MapperScannerConfigurer mapperScannerConfigurer() {
         MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
         mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
         mapperScannerConfigurer.setBasePackage("com.example.demo.dao");
         return mapperScannerConfigurer;
     }
     
 }
