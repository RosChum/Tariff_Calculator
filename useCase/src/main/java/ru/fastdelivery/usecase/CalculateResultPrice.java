package ru.fastdelivery.usecase;

import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shippingDistance.Distance;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateResultPrice {

    public static Price getResulPrice(Shipment shipment, Distance distance, TariffCalculateUseCase tariffCalculateUseCase) {
        Price priceByCubicMeter = tariffCalculateUseCase.calcByCubicMeter(shipment);
        Price priceByWeight = tariffCalculateUseCase.calcByWeight(shipment);
        Price basePrice = priceByCubicMeter.amount().compareTo(priceByWeight.amount()) > 0 ? priceByCubicMeter : priceByWeight;
        if (distance.getDistanceInKilometers().compareTo(new BigDecimal(450)) > 0) {
            return basePrice.getNewPrice(distance.getDistanceInKilometers()
                    .divide(new BigDecimal(450), 2, RoundingMode.CEILING)
                    .multiply(basePrice.amount()).setScale(2, RoundingMode.CEILING));
        } else {
            return basePrice.getNewPrice(basePrice.amount().setScale(2, RoundingMode.CEILING));
        }
    }

}
