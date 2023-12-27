package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequiredArgsConstructor
public class TariffCalculateUseCase {
    private final WeightPriceProvider weightPriceProvider;
    public Price calcByWeight(Shipment shipment) {
        var weightAllPackagesKg = shipment.weightAllPackages().kilograms();
        var minimalPrice = weightPriceProvider.minimalPrice();

        return weightPriceProvider
                .costPerKg()
                .multiply(weightAllPackagesKg)
                .max(minimalPrice);
    }

    public Price calcByCubicMeter(Shipment shipment) {
        BigDecimal cubicMeterAllPackages = shipment.cubicMeterAllPackages().cubicMeter();
        Price minimalPrice = weightPriceProvider.minimalPrice();
        return weightPriceProvider
                .costPerCubicMeter()
                .multiply(cubicMeterAllPackages)
                .max(minimalPrice);

    }

    public Price minimalPrice() {
        return weightPriceProvider.minimalPrice();
    }
}
