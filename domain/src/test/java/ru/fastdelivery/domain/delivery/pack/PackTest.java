package ru.fastdelivery.domain.delivery.pack;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.fastdelivery.domain.common.height.Height;
import ru.fastdelivery.domain.common.length.Length;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.common.width.Width;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PackTest {

    @Test
    void whenWeightMoreThanMaxWeight_thenThrowException() {
        var weight = new Weight(BigInteger.valueOf(150_001));
        assertThatThrownBy(() -> new Pack(weight, new Height(BigInteger.valueOf(120)), new Length(BigInteger.valueOf(120)), new Width(BigInteger.valueOf(120))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "Проверка соответствия параметров созданного объекта")
    void whenParametersObjectLessThanMax_thenObjectCreated() {
        var actual = new Pack(new Weight(BigInteger.valueOf(1000)), new Height(BigInteger.valueOf(120)), new Length(BigInteger.valueOf(120)), new Width(BigInteger.valueOf(120)));
        assertThat(actual.weight()).isEqualTo(new Weight(BigInteger.valueOf(1_000)));
        assertThat(actual.height()).isEqualTo(new Height(BigInteger.valueOf(120)));
        assertThat(actual.length()).isEqualTo(new Length(BigInteger.valueOf(120)));
        assertThat(actual.width()).isEqualTo(new Width(BigInteger.valueOf(120)));
    }

    @ParameterizedTest(name = "Высота, длина или ширина больше лимита = {arguments} ->  исключение")
    @CsvSource({"1600, 150, 200",
            "200, 1700, 150",
            "700, 100, 1800"})
    void whenHeightOrLengthOrWidthMoreMaxValue_thenException(BigInteger height, BigInteger length, BigInteger width  ){

        Assertions.assertThatThrownBy(() -> new Pack(new Weight(BigInteger.valueOf(1000)), new Height(height), new Length(length), new Width(width)))
                .isInstanceOf(IllegalArgumentException.class);

    }


}