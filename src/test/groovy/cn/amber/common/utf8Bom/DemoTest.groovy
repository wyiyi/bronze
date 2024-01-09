package cn.amber.common.utf8Bom

import jdk.nashorn.internal.ir.annotations.Ignore
import org.junit.Test
import org.springframework.test.context.jdbc.Sql

import static org.junit.jupiter.api.Assertions.assertThrows
import static org.junit.jupiter.api.Assertions.assertTrue

class DemoTest {

    @Test
    @Sql("/cn/amber/common/utf8Bom/init.sql")
    void test(){
        assert true;
    }

    @Test
    @Sql("/cn/amber/common/utf8Bom/insert.sql")
    void insert(){
        assert true;
    }

    @Ignore
    @Sql("/cn/amber/common/utf8Bom/utf8bom.sql")
    void testBom(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Integer.parseInt("1a");
        });

        String expectedMessage = "Failed to execute SQL script statement";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}
