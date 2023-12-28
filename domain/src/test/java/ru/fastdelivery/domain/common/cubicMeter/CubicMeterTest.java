package ru.fastdelivery.domain.common.cubicMeter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CubicMeterTest {

    @Test
    @DisplayName(value = "Проверка метода add -> добавленные кубические метры суммируются")
    void whenAddCubicMeter_thenCubicMeterIsIncreased(){
        CubicMeter cubicMeter = new CubicMeter(new BigDecimal("20.054"));
        CubicMeter actual = cubicMeter.add(new CubicMeter(new BigDecimal("1.1")));
        Assertions.assertThat(actual.cubicMeter()).isEqualTo(new BigDecimal("21.154"));
    }

}
