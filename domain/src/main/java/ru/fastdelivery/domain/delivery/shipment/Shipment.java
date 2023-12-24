package ru.fastdelivery.domain.delivery.shipment;

import ru.fastdelivery.domain.common.currency.Currency;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;

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
   private final static Logger logger = Logger.getLogger(String.valueOf(Shipment.class));
    public Weight weightAllPackages() {
        logger.log(Level.INFO," start Shipment " + this);
        return packages.stream()
                .map(Pack::weight)
                .reduce(Weight.zero(), Weight::add);
    }
}
