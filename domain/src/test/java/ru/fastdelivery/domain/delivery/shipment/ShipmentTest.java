package ru.fastdelivery.domain.delivery.shipment;

import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.cubicMeter.CubicMeter;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.height.Height;
import ru.fastdelivery.domain.common.length.Length;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.common.width.Width;
import ru.fastdelivery.domain.delivery.pack.Pack;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShipmentTest {

    @Test
    void whenSummarizingWeighAndCubicMeterstOfAllPackages_thenReturnSum() {
        var weight1 = new Weight(BigInteger.TEN);
        var weight2 = new Weight(BigInteger.ONE);

        var packages = List.of(new Pack(weight1, new Height(BigInteger.valueOf(234)), new Length(BigInteger.valueOf(345)), new Width(BigInteger.valueOf(589))), new Pack(weight2, new Height(BigInteger.valueOf(234)), new Length(BigInteger.valueOf(345)), new Width(BigInteger.valueOf(589))));
        var shipment = new Shipment(packages, new CurrencyFactory(code -> true).create("RUB"));

        var massOfShipment = shipment.weightAllPackages();
        CubicMeter cubicMetersOfShipment = shipment.cubicMeterAllPackages();

        assertThat(massOfShipment.weightGrams()).isEqualByComparingTo(BigInteger.valueOf(11));
        assertThat(cubicMetersOfShipment.cubicMeter()).isEqualTo(new BigDecimal("0.1050"));
    }

}