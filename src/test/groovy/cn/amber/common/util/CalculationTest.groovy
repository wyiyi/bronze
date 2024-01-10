package cn.amber.common.util

import org.junit.jupiter.api.Test

import static com.amber.common.util.Calculation.add
import static com.amber.common.util.Calculation.divide
import static com.amber.common.util.Calculation.multiply
import static com.amber.common.util.Calculation.subtract

class CalculationTest {

    @Test
    void calculation() {
        double d1 = 0.06;
        double d2 = 0.01;
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        assert b1.add(b2).doubleValue() != 0.07;

        assert add(d1, d2) == 0.07;
        assert subtract(d1, d2) == 0.05;
        assert multiply(d1, d2) == 0.0006;
        assert divide(d1, d2) == 6;

    }
}
