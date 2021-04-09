package com.amber.demo

import jdk.nashorn.internal.ir.annotations.Ignore
import org.junit.jupiter.api.BeforeEach
import org.junit.Test
import org.springframework.test.context.jdbc.Sql

import static org.junit.jupiter.api.Assertions.assertThrows
import static org.junit.jupiter.api.Assertions.assertTrue

class DemoTest {
    @BeforeEach
    @Sql("/com/amber/demo/init.sql")
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
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            Integer.parseInt("1a");
//        });
//
//        String expectedMessage = "Failed to execute SQL script statement";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));

    }

}
