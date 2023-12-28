package ru.fastdelivery.domain.common.length;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

public class LengthTest {

    @Test
    @DisplayName("Создание отрицательной длины - > исключение")
    void whenMillimetersBelowZero_thenException() {
        Assertions.assertThatThrownBy(() -> new Length(new BigInteger("-1")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Сопоставление объектов по HashCode")
    void equalsTypeHeight_same() {
        Length length = new Length(new BigInteger("100"));
        Length length1 = new Length(new BigInteger("100"));
        Assertions.assertThat(length)
                .isEqualTo(length1).hasSameHashCodeAs(length1);

    }

    @Test
    @DisplayName("Проверка равенства объекта null")
    void equalsNull() {
        Length length = new Length(new BigInteger("100"));
        Assertions.assertThat(length).isNotEqualTo(null);
    }

    @ParameterizedTest(name = "Проверка компаратора {arguments}")
    @CsvSource({"1000, 1, -1",
            "199, 199, 0",
            "50, 999, 1"})
    void compareTo(BigInteger low, BigInteger high, int expected) {
        Length length = new Length(low);
        Length length1 = new Length(high);
        Assertions.assertThat(length.compareTo(length1)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Первая длина больше второй -> true")
    void whenFirstHeightGreaterSecondHeight_thenTrue() {
        Length length = new Length(new BigInteger("1000"));
        Length length1 = new Length(new BigInteger("100"));
        Assertions.assertThat(length.greaterThan(length1.length())).isTrue();

    }
}
