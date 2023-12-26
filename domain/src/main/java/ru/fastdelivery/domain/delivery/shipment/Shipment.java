package ru.fastdelivery.domain.delivery.shipment;

import ru.fastdelivery.domain.common.cubicMeter.CubicMeter;
import ru.fastdelivery.domain.common.cubicMeter.CubicMeterCalculation;
import ru.fastdelivery.domain.common.currency.Currency;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param packages упаковки в грузе
 * @param currency валюта объявленная для груза
 */
public record Shipment(
        List<Pack> packages,
        Currency currency
) {

    public Weight weightAllPackages() {

        return packages.stream()
                .map(Pack::weight)
                .reduce(Weight.zero(), Weight::add);
    }

    public CubicMeter cubicMeterAllPackages() {
        return packages.stream().map(pack ->
                new CubicMeter(new CubicMeterCalculation(pack.height(), pack.length(), pack.width()).calculateCubicMeter())
        ).reduce(new CubicMeter(BigDecimal.ZERO), CubicMeter::add);

    }


}
