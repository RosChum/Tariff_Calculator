package ru.fastdelivery.domain.common.height;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HeightFactoryTest {


    @ParameterizedTest(name = "Миллиметры высоты = {arguments} -> объект создан")
    @ValueSource(longs = {0, 1, 100, 1000})
    void whenMillimetersGreaterThanZero_thenObjectCreated(long amount) {
        Height height = new Height(BigInteger.valueOf(amount));
        assertNotNull(height);
        assertThat(height.height()).isEqualByComparingTo(BigInteger.valueOf(amount));
    }


    @ParameterizedTest(name = "Миллиметры высоты = {arguments} -> исключение")
    @ValueSource(longs = {-1, -100, -1600})
    void whenMillimetersLessThanZero_thenThrowException(long amount) {
        assertThatThrownBy(() -> new Height(BigInteger.valueOf(amount)))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
