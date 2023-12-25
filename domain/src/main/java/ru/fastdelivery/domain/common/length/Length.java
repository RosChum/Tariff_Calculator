package ru.fastdelivery.domain.common.length;

import ru.fastdelivery.domain.common.height.Height;

import java.math.BigInteger;
/**
 * Общий класс длины
 *
 * @param length длина в миллиметрах
 */
public record Length(BigInteger length) implements Comparable<Length> {

    public Length {
        if (isLessThanZero(length)) {
            throw new IllegalArgumentException("Length cannot be below Zero!");
        }
    }

    private static boolean isLessThanZero(BigInteger length) {
        return BigInteger.ZERO.compareTo(length) > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Length length1 = (Length) o;
        return length.compareTo(length1.length) == 0;
    }


    @Override
    public int compareTo(Length o) {
        return o.length.compareTo(length);
    }
    public boolean greaterThan(BigInteger size) {
        return length.compareTo(size) > 0;
    }

}
