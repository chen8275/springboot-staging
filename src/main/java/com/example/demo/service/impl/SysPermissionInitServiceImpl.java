package com.example.demo.service.impl;

import com.example.demo.core.universal.AbstractService;
import com.example.demo.dao.SysPermissionInitMapper;
import com.example.demo.model.SysPermissionInit;
import com.example.demo.service.SysPermissionInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
* @Description: SysPermissionInitService接口实现类
* @author 张瑶
* @date 2018/05/18 14:37
*/
@Service
public class SysPermissionInitServiceImpl extends AbstractService<SysPermissionInit> implements SysPermissionInitService {

    @Autowired
    SysPermissionInitMapper sysPermissionInitMapper;

    @Override
    public List<SysPermissionInit> selectAllOrderBySort(){
        return sysPermissionInitMapper.selectAllOrderBySort();
    }

}