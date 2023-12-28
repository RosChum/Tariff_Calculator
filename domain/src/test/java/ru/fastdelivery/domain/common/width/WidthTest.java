package ru.fastdelivery.domain.common.width;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

public class WidthTest {
    @Test
    @DisplayName("Создание отрицательной ширины - > исключение")
    void whenMillimetersBelowZero_thenException() {
        Assertions.assertThatThrownBy(() -> new Width(new BigInteger("-1")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Сопоставление объектов по HashCode")
    void equalsTypeHeight_same() {
        Width width = new Width(new BigInteger("100"));
        Width width1 = new Width(new BigInteger("100"));
        Assertions.assertThat(width)
                .isEqualTo(width1).hasSameHashCodeAs(width1);

    }

    @Test
    @DisplayName("Проверка равенства объекта null")
    void equalsNull() {
        Width width = new Width(new BigInteger("100"));
        Assertions.assertThat(width).isNotEqualTo(null);
    }

    @ParameterizedTest(name = "Проверка компаратора {arguments}")
    @CsvSource({"1000, 1, -1",
            "199, 199, 0",
            "50, 999, 1"})
    void compareTo(BigInteger low, BigInteger high, int expected) {
        Width width = new Width(low);
        Width width1 = new Width(high);
        Assertions.assertThat(width.compareTo(width1)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Первая ширины больше второй -> true")
    void whenFirstHeightGreaterSecondHeight_thenTrue() {
        Width width = new Width(new BigInteger("1000"));
        Width width1 = new Width(new BigInteger("100"));
        Assertions.assertThat(width.greaterThan(width1.width())).isTrue();

    }

}
