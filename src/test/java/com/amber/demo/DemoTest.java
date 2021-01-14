package com.amber.demo;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
class DemoTest {

    @Test
    @Sql("/com/amber/demo/init.sql")
    void test(){
       assert true;
    }

    @Test
    @Sql("/com/amber/demo/utf8bom.sql")
    void testBom(){
        assert false;
    }

    @Test
    @Sql("/com/amber/demo/insert.sql")
    void insert(){
        assert true;
    }
}
