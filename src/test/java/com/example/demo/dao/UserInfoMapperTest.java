package com.example.demo.dao;

import com.example.demo.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author chendesheng chendesheng@tuhu.cn
 * @since 2019/5/6 10:54
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserInfoMapperTest {
    @Autowired
    UserInfoMapper userInfoMapper;
    
    @Test
    public void selectByPrimaryKey() {
    
        UserInfo userInfo = new UserInfo();
        userInfo.setId("1");
        log.info("得到:[{}]",userInfo);
    }
}