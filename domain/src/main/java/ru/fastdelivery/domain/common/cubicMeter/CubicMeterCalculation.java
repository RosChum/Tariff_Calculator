package ru.fastdelivery.domain.common.cubicMeter;

import lombok.experimental.UtilityClass;
import ru.fastdelivery.domain.common.height.Height;
import ru.fastdelivery.domain.common.length.Length;
import ru.fastdelivery.domain.common.width.Width;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public record CubicMeterCalculation(Height height, Length length, Width width) {

    public BigDecimal calculateCubicMeter() {

        return new BigDecimal(getAroundValue(length.length()).multiply(getAroundValue(width.width()))
                .multiply(getAroundValue(height.height())).divide(new BigInteger("1000000000"))).setScale(4, RoundingMode.UP);

    }

    private BigInteger getAroundValue(BigInteger bigInteger) {
        return bigInteger.divideAndRemainder(new BigInteger("50"))[1].compareTo(new BigInteger("25")) < 0 ?
                bigInteger.divide(new BigInteger("50")).multiply(new BigInteger("50"))
                : bigInteger.divide(new BigInteger("50")).add(BigInteger.ONE).multiply(new BigInteger("50"));
    }
}
