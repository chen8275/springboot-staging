package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
    
    /**
     * 主键
     */
    @Id
    private String id;
    
    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;
    
    private String password;
    
    /**
     * 加密盐值
     */
    private String salt;
    
    /**
     * 用户所有角色值，用于shiro做角色权限的判断
     */
    @Transient
    private Set<String> roles;
    
    /**
     * 用户所有权限值，用于shiro做资源权限的判断
     */
    @Transient
    private Set<String> perms;
    
    
}