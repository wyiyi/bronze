package com.amber.common.interceptor;

import com.amber.common.handler.CryptHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CryptoInterceptor implements IInnerInterceptor {


    private final CryptHandler cryptHandler;

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        if (parameter != null) {
            log.debug("加密前参数：" + parameter);
            cryptHandler.handleParam(parameter);
            log.debug("加密后参数：" + parameter);
        }
    }

    @Override
    public void afterQuery(List<Object> resultList) {
        if (resultList != null && !resultList.isEmpty()) {
            log.debug("解密前结果：" + resultList);
            cryptHandler.handleResultList(resultList);
            log.debug("解密后结果：" + resultList);
        }
    }

    @Override
    public void beforeUpdate(Object parameter) {
        if (parameter != null) {
            log.debug("加密前参数：" + parameter);
            cryptHandler.handleParam(parameter);
            log.debug("加密后参数：" + parameter);
        }
    }

}
