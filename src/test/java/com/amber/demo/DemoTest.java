package com.amber.demo;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

class DemoTest {

    @Sql("/com/amber/demo/init.sql")
    @BeforeEach
    void test(){
       assert true;
    }

    @Test
    @Sql("/com/amber/demo/insert.sql")
    void insert(){
        assert true;
    }

    @Ignore
    @Sql("/com/amber/demo/utf8bom.sql")
    void testBom(){
        assert true;
    }


}
