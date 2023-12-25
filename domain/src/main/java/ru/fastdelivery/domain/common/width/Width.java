package ru.fastdelivery.domain.common.width;

import ru.fastdelivery.domain.common.length.Length;

import java.math.BigInteger;
/**
 * Общий класс ширины
 *
 * @param width ширина в миллиметрах
 */
public record Width(BigInteger width) implements Comparable<Width> {

    public Width {
        if (isLessThanZero(width)) {
            throw new IllegalArgumentException("Width cannot be below Zero!");
        }
    }

    private static boolean isLessThanZero(BigInteger width) {
        return BigInteger.ZERO.compareTo(width) > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Width width1 = (Width) o;
        return width.compareTo(width1.width) == 0;
    }


    @Override
    public int compareTo(Width o) {
        return o.width.compareTo(width);
    }

    public boolean greaterThan(BigInteger size) {
        return width.compareTo(size) > 0;
    }
}
