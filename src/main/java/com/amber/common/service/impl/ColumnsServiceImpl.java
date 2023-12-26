package com.amber.common.service.impl;

import com.amber.common.entity.Columns;
import com.amber.common.mapper.ColumnsMapper;
import com.amber.common.service.ColumnsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ColumnsServiceImpl extends ServiceImpl<ColumnsMapper, Columns> implements ColumnsService {

    public boolean add(Columns columns){
        return super.save(columns);
    }

}
