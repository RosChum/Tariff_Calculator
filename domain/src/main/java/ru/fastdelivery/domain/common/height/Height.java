package ru.fastdelivery.domain.common.height;

import ru.fastdelivery.domain.common.weight.Weight;

import java.math.BigInteger;

/**
 * Общий класс высоты
 *
 * @param height высоты в миллиметрах
 */
public record Height(BigInteger height) implements Comparable<Height> {

    public Height {
        if (isLessThanZero(height)) {
            throw new IllegalArgumentException("Height cannot be below Zero!");
        }
    }

    private static boolean isLessThanZero(BigInteger height) {
        return BigInteger.ZERO.compareTo(height) > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Height height1 = (Height) o;
        return height.compareTo(height1.height) == 0;
    }


    @Override
    public int compareTo(Height o) {
        return o.height.compareTo(height);
    }

    public boolean greaterThan(BigInteger size) {
        return height.compareTo(size) > 0;
    }


}
