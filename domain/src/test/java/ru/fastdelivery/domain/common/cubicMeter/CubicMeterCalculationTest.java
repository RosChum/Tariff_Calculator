package ru.fastdelivery.domain.common.cubicMeter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.height.Height;
import ru.fastdelivery.domain.common.length.Length;
import ru.fastdelivery.domain.common.width.Width;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CubicMeterCalculationTest {

    @Test
    @DisplayName(value = "Проверка получения кубического метра")
    void whenCalculate_thenValueCubicMeter(){
        CubicMeterCalculation cubicMeterCalculation = new CubicMeterCalculation(new Height(BigInteger.valueOf(234)), new Length(BigInteger.valueOf(345)), new Width(BigInteger.valueOf(589)));
        Assertions.assertThat(cubicMeterCalculation.calculateCubicMeter()).isEqualTo(new BigDecimal("0.0525"));
    }
}
