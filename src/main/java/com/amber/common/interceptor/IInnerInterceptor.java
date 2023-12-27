package com.amber.common.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface IInnerInterceptor {

    /**
     * 查询前置处理
     *
     * @param executor
     * @param ms
     * @param parameter
     * @param rowBounds
     * @param resultHandler
     * @param boundSql
     */
    default void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
    }

    /**
     * 查询后置处理
     *
     * @param resultList
     */
    default void afterQuery(List<Object> resultList) {
    }

    /**
     * 更新前置处理
     *
     * @param parameter
     */
    default void beforeUpdate(Object parameter) {
    }


}
