package com.example.demo.dao;

import com.example.demo.model.UserInfo;

import java.util.List;

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
    
    /**
     * 查询所有用户
     * @return
     */
    List<UserInfo> selectAll();
    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}