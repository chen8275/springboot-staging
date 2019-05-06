package com.example.demo.model;

import javax.persistence.*;

@Table(name = "user_role")
public class UserRole {
    
    @Id
    private Integer userId;

    @Column(name = "role_id")
    private Integer roleId;

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}