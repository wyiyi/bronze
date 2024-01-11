package cn.amber.common.utf8Bom

import jdk.nashorn.internal.ir.annotations.Ignore
import org.junit.Test
import org.springframework.test.context.jdbc.Sql

import static org.junit.jupiter.api.Assertions.assertThrows
import static org.junit.jupiter.api.Assertions.assertTrue

@Sql
class DemoTest {

    @Test
    void test(){
        assert true;
    }

    @Test
    void insert(){
        assert true;
    }

    @Ignore
    void testBom(){
        Exception exception = assertThrows(RuntimeException.class, { ->
            Integer.parseInt("1a");
        });

        String expectedMessage = "Failed to execute SQL script statement";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}
