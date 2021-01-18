package com.amber.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DemoTest {

    @Test
    @Sql("/com/amber/demo/init.sql")
    void test(){
       assert true;
    }

    @Test
    @Sql("/com/amber/demo/insert.sql")
    void insert(){
        assert true;
    }

    @Test
    @Sql("/com/amber/demo/utf8bom.sql")
    void testBom(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Integer.parseInt("1a");
        });

        String expectedMessage = "Failed to execute SQL script statement";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }


}
