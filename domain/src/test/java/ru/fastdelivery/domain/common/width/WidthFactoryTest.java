package ru.fastdelivery.domain.common.width;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WidthFactoryTest {

    @ParameterizedTest(name = "Миллиметры ширины = {arguments} -> объект создан")
    @ValueSource(longs = {0, 1, 100, 1000})
    void whenMillimetersGreaterThanZero_thenObjectCreated(long amount) {
        Width width = new Width(BigInteger.valueOf(amount));
        assertNotNull(width);
        assertThat(width.width()).isEqualByComparingTo(BigInteger.valueOf(amount));
    }


    @ParameterizedTest(name = "Миллиметры ширины = {arguments} -> исключение")
    @ValueSource(longs = {-1, -100, -1600})
    void whenMillimetersLessThanZero_thenThrowException(long amount) {
        assertThatThrownBy(() -> new Width(BigInteger.valueOf(amount)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
