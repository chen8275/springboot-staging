package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
    
    /**
     * 主键
     */
    @Id
    private Integer id;
    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;
    
}