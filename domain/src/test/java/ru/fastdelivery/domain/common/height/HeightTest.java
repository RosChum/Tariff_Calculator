package ru.fastdelivery.domain.common.height;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

public class HeightTest {

    @Test
    @DisplayName("Создание отрицательной высоты - > исключение")
    void whenMillimetersBelowZero_thenException() {
        Assertions.assertThatThrownBy(() -> new Height(new BigInteger("-1")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Сопоставление объектов по HashCode")
    void equalsTypeHeight_same() {
        Height height = new Height(new BigInteger("100"));
        Height height1 = new Height(new BigInteger("100"));

        Assertions.assertThat(height)
                .isEqualTo(height1).hasSameHashCodeAs(height1);

    }

    @Test
    @DisplayName("Проверка равенства объекта null")
    void equalsNull() {
        Height height = new Height(new BigInteger("100"));
        Assertions.assertThat(height).isNotEqualTo(null);
    }

    @ParameterizedTest(name = "Проверка компаратора {arguments}")
    @CsvSource({"1000, 1, -1",
            "199, 199, 0",
            "50, 999, 1"})
    void compareTo(BigInteger low, BigInteger high, int expected) {
        Height height = new Height(low);
        Height height1 = new Height(high);
        Assertions.assertThat(height.compareTo(height1)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Первая высота больше второй -> true")
    void whenFirstHeightGreaterSecondHeight_thenTrue() {
        Height height = new Height(new BigInteger("1000"));
        Height height1 = new Height(new BigInteger("100"));
        Assertions.assertThat(height.greaterThan(height1.height())).isTrue();

    }
}
