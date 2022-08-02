package com.amber.common.utf8bom;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DemoTest {

    @BeforeEach
    @Sql("/com/amber/common/init.sql")
    void test(){
       assert true;
    }

    @Test
    @Sql("/com/amber/common/insert.sql")
    void insert(){
        assert true;
    }

    @Ignore
    @Sql("/com/amber/common/utf8bom.sql")
    void testBom(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Integer.parseInt("1a");
        });

        String expectedMessage = "Failed to execute SQL script statement";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }


}
