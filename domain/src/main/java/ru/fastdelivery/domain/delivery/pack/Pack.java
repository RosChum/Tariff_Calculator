package ru.fastdelivery.domain.delivery.pack;

import ru.fastdelivery.domain.common.height.Height;
import ru.fastdelivery.domain.common.length.Length;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.common.width.Width;

import java.math.BigInteger;

/**
 * Упаковка груза
 *
 * @param weight вес товаров в упаковке
 */
public record Pack(Weight weight, Height height, Length length, Width width) {

    private static final Weight maxWeight = new Weight(BigInteger.valueOf(150_000));
    private static final BigInteger maxSize = new BigInteger(String.valueOf(1500));

    public Pack {
        if (weight.greaterThan(maxWeight)) {
            throw new IllegalArgumentException("Package can't be more than " + maxWeight);
        }
        if (height().greaterThan(maxSize) || length.greaterThan(maxSize)|| width().greaterThan(maxSize)) {
            throw new IllegalArgumentException("Height or length or width package can't be more than " + maxSize);

        }

    }
}
