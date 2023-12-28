package ru.fastdelivery.domain.common.cubicMeter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CubicMeterFactoryTest {

    @Test
    @DisplayName(value = "Создание объекта")
    void whenParameterObjectGreaterZero_thenObjectCreated(){
        CubicMeter cubicMeter = new CubicMeter(new BigDecimal("25.3564"));
        Assertions.assertNotNull(cubicMeter);
        org.assertj.core.api.Assertions.assertThat(cubicMeter.cubicMeter()).isEqualByComparingTo(new BigDecimal("25.3564"));

    }
}
