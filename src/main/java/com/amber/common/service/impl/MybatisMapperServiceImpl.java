package com.amber.common.service.impl;

import com.amber.common.entity.MybatisDemo;
import com.amber.common.mapper.MybatisMapper;
import com.amber.common.service.MybatisMapperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MybatisMapperServiceImpl implements MybatisMapperService {
    @Resource
    MybatisMapper mybatisMapper;

    public void updateById(MybatisDemo mybatisDemo){
        mybatisMapper.updateById(mybatisDemo);
    }
}
