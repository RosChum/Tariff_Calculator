package ru.fastdelivery.domain.delivery.shippingDistance;

import org.geotools.geometry.DirectPosition2D;
import org.geotools.referencing.GeodeticCalculator;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DistanceTest {


    @Test
    @DisplayName(value = "Проверка расчета километров")
    void whenAllParametersCorrect_thenReturnKilometers() {

        DirectPosition2D departure = new DirectPosition2D(37.608970, 55.752184);
        DirectPosition2D destination = new DirectPosition2D(44.002981, 56.330889);

        GeodeticCalculator geodeticCalculator = new GeodeticCalculator(DefaultGeographicCRS.WGS84);
        geodeticCalculator.setStartingGeographicPoint(departure);
        geodeticCalculator.setDestinationGeographicPoint(destination);
        int actual = BigDecimal.valueOf(geodeticCalculator.getOrthodromicDistance() / 1000).setScale(3, RoundingMode.CEILING).compareTo(new BigDecimal(403.535).setScale(3, RoundingMode.FLOOR));
        int expected = 0;

        Assertions.assertEquals(actual, expected);

    }

}
