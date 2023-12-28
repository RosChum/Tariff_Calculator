package ru.fastdelivery.domain.common.cubicMeter;

import ru.fastdelivery.domain.common.height.Height;
import ru.fastdelivery.domain.common.length.Length;
import ru.fastdelivery.domain.common.width.Width;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public record CubicMeterCalculation(Height height, Length length, Width width) {

    public BigDecimal calculateCubicMeter() {

        return getAroundValue(length.length()).multiply(getAroundValue(width.width()))
                .multiply(getAroundValue(height.height())).divide(new BigDecimal("1000000000"), 4, RoundingMode.CEILING);

    }

    private BigDecimal getAroundValue(BigInteger bigInteger) {
        return new BigDecimal(bigInteger.divideAndRemainder(new BigInteger("50"))[1].compareTo(new BigInteger("25")) < 0 ?
                bigInteger.divide(new BigInteger("50")).multiply(new BigInteger("50"))
                : bigInteger.divide(new BigInteger("50")).add(BigInteger.ONE).multiply(new BigInteger("50")));
    }
}
