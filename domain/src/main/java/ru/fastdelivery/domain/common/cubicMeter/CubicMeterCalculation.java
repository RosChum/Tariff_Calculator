package ru.fastdelivery.domain.common.cubicMeter;

import ru.fastdelivery.domain.common.height.Height;
import ru.fastdelivery.domain.common.length.Length;
import ru.fastdelivery.domain.common.width.Width;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;

public record CubicMeterCalculation(Height height, Length length, Width width) {
    private static final Logger logger = Logger.getAnonymousLogger();

    public BigDecimal calculateCubicMeter() {

        BigDecimal result = getAroundValue(length.length()).multiply(getAroundValue(width.width()))
                .multiply(getAroundValue(height.height())).divide(new BigDecimal("1000000000"), 4, RoundingMode.CEILING);

        logger.log(Level.INFO, "length.length()" + length.length() + "width.width()" + width.width() + "height.height()" + height.height() + "result " + result);
        return result;

    }

    private BigDecimal getAroundValue(BigInteger bigInteger) {
        return new BigDecimal(bigInteger.divideAndRemainder(new BigInteger("50"))[1].compareTo(new BigInteger("25")) < 0 ?
                bigInteger.divide(new BigInteger("50")).multiply(new BigInteger("50"))
                : bigInteger.divide(new BigInteger("50")).add(BigInteger.ONE).multiply(new BigInteger("50")));
    }
}
