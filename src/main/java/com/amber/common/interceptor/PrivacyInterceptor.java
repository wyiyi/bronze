package com.amber.common.interceptor;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
                @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        }
)
public class PrivacyInterceptor implements Interceptor {

    @Setter
    private List<IInnerInterceptor> iInnerInterceptorList = new ArrayList<>();

    public Object intercept(Invocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter = args[1];


        switch (method.getName()) {
            case "update":
                updateHandle(parameter);
                return invocation.proceed();
            case "query":
                RowBounds rowBounds = (RowBounds) args[2];
                ResultHandler<Object> resultHandler = (ResultHandler) args[3];
                Executor executor = (Executor) invocation.getTarget();

                CacheKey cacheKey;
                BoundSql boundSql;
                //由于逻辑关系，只会进入一次
                if (args.length == 4) {
                    //4 个参数时
                    boundSql = ms.getBoundSql(parameter);
                    cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
                } else {
                    //6 个参数时
                    cacheKey = (CacheKey) args[4];
                    boundSql = (BoundSql) args[5];
                }
                return selectHandle(executor, ms, parameter, rowBounds, resultHandler, boundSql, cacheKey);
            default:
                return invocation.proceed();
        }
    }

    /**
     * 查询操作处理
     *
     * @param executor
     * @param ms
     * @param parameter
     * @param rowBounds
     * @param resultHandler
     * @param boundSql
     * @param cacheKey
     * @return
     * @throws Throwable
     */
    protected Object selectHandle(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql, CacheKey cacheKey) throws Throwable {

        //查询前置处理
        if (iInnerInterceptorList != null && !iInnerInterceptorList.isEmpty()) {
            iInnerInterceptorList.forEach(iInnerInterceptor -> iInnerInterceptor.beforeQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql));
        }
        List<Object> resultList = executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
        // 查询后置处理
        if (iInnerInterceptorList != null && !iInnerInterceptorList.isEmpty()) {
            iInnerInterceptorList.forEach(iInnerInterceptor -> iInnerInterceptor.afterQuery(resultList));
        }
        return resultList;
    }

    /**
     * 新增修改操作处理
     *
     * @param parameter
     * @return
     */
    protected void updateHandle(Object parameter) {

        //查询前置处理
        if (iInnerInterceptorList != null && !iInnerInterceptorList.isEmpty()) {
            iInnerInterceptorList.forEach(iInnerInterceptor -> iInnerInterceptor.beforeUpdate(parameter));
        }
    }


}
