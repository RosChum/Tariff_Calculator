package ru.fastdelivery.domain.common.length;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LengthFactoryTest {
    @ParameterizedTest(name = "Миллиметры длины = {arguments} - > объект создан")
    @ValueSource(longs = {0, 10, 100, 1500})
    void whenMillimetersGreaterThenZero_thenObjectCreated(long amount) {
        Length length = new Length(BigInteger.valueOf(amount));
        assertNotNull(length);
        assertThat(length.length()).isEqualByComparingTo(BigInteger.valueOf(amount));

    }

    @ParameterizedTest(name = "Миллиметры длины = {arguments} -> исключение")
    @ValueSource(longs = {-1, -10, -100, -1600})
    void whenMillimetersLessZero_thenException(long amount) {
        Assertions.assertThatThrownBy(() -> new Length(BigInteger.valueOf(amount)))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
