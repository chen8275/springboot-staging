 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.configuration;

 import com.alibaba.druid.pool.DruidDataSource;
 import lombok.extern.slf4j.Slf4j;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;

 import javax.sql.DataSource;
 import java.sql.SQLException;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 11:26
  */
 @Configuration
 @Slf4j
 public class DruidDataSourceConfiguration {
     
     @Value("${spring.datasource.url}")
     private String dbUrl;
    
     @Value("${spring.datasource.username}")
     private String username;
    
     @Value("${spring.datasource.password}")
     private String password;
    
     @Value("${spring.datasource.driverClassName}")
     private String driverClassName;
    
     @Value("${spring.datasource.initialSize}")
     private int initialSize;
    
     @Value("${spring.datasource.minIdle}")
     private int minIdle;
    
     @Value("${spring.datasource.maxActive}")
     private int maxActive;
    
     @Value("${spring.datasource.maxWait}")
     private int maxWait;
    
     @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
     private int timeBetweenEvictionRunsMillis;
    
     @Value("${spring.datasource.minEvictableIdleTimeMillis}")
     private int minEvictableIdleTimeMillis;
    
     @Value("${spring.datasource.validationQuery}")
     private String validationQuery;
    
     @Value("${spring.datasource.testWhileIdle}")
     private boolean testWhileIdle;
    
     @Value("${spring.datasource.testOnBorrow}")
     private boolean testOnBorrow;
    
     @Value("${spring.datasource.testOnReturn}")
     private boolean testOnReturn;
    
     @Value("${spring.datasource.poolPreparedStatements}")
     private boolean poolPreparedStatements;
    
     @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
     private int maxPoolPreparedStatementPerConnectionSize;
    
     @Value("${spring.datasource.filters}")
     private String filters;
    
     @Value("{spring.datasource.connectionProperties}")
     private String connectionProperties;
    
     @Bean
     public DataSource getDataSource() {
         
         DruidDataSource datasource = new DruidDataSource();
         datasource.setUrl(this.dbUrl);
         datasource.setUsername(username);
         datasource.setPassword(password);
         datasource.setDriverClassName(driverClassName);
        
         //configuration
         datasource.setInitialSize(initialSize);
         datasource.setMinIdle(minIdle);
         datasource.setMaxActive(maxActive);
         datasource.setMaxWait(maxWait);
         datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
         datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
         datasource.setValidationQuery(validationQuery);
         datasource.setTestWhileIdle(testWhileIdle);
         datasource.setTestOnBorrow(testOnBorrow);
         datasource.setTestOnReturn(testOnReturn);
         datasource.setPoolPreparedStatements(poolPreparedStatements);
         datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
         try {
             datasource.setFilters(filters);
         } catch (SQLException e) {
             log.error("druid configuration initialization filter", e);
         }
         datasource.setConnectionProperties(connectionProperties);
        
         return datasource;
     }
     
 }
