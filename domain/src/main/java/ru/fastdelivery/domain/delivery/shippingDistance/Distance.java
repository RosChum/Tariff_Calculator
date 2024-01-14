package ru.fastdelivery.domain.delivery.shippingDistance;

import org.geotools.geometry.DirectPosition2D;
import org.geotools.referencing.GeodeticCalculator;
import org.geotools.referencing.crs.DefaultGeographicCRS;

import java.math.BigDecimal;

public record Distance(BigDecimal latitudeDeparture,
                       BigDecimal longitudeDeparture,
                       BigDecimal latitudeDestination,
                       BigDecimal longitudeDestination) {
    public BigDecimal getDistanceInKilometers() {

        DirectPosition2D departure = new DirectPosition2D(longitudeDeparture.doubleValue(), latitudeDeparture.doubleValue());
        DirectPosition2D destination = new DirectPosition2D(longitudeDestination.doubleValue(), latitudeDestination.doubleValue());

        GeodeticCalculator geodeticCalculator = new GeodeticCalculator(DefaultGeographicCRS.WGS84);
        geodeticCalculator.setStartingGeographicPoint(departure);
        geodeticCalculator.setDestinationGeographicPoint(destination);

        return BigDecimal.valueOf(geodeticCalculator.getOrthodromicDistance() / 1000);
    }
}
