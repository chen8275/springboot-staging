package com.example.demo.dao;

import com.example.demo.model.UserInfo;

public interface UserInfoMapper {
    
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);
    
    /**
     * 通过id查找
     * @param id
     * @return
     */
    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}