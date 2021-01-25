package com.amber.demo;

import org.junit.Before;
import org.junit.Test;

class DemoTest {

    @Before
    @Sql("/com/amber/demo/init.sql")
    void test(){
       assert true;
    }

    @Test
    @Sql("/com/amber/demo/insert.sql")
    void insert(){
        assert true;
    }

    @Test(expected = RuntimeException.class)
    @Sql("/com/amber/demo/utf8bom.sql")
    void testBom(){

    }


}
