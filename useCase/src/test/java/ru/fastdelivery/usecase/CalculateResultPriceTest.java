package ru.fastdelivery.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.fastdelivery.domain.common.currency.Currency;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;
import ru.fastdelivery.domain.delivery.shippingDistance.Distance;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CalculateResultPriceTest {
    @Mock
    Shipment shipment;
    @Mock
    Distance distance;
    @Mock
    TariffCalculateUseCase tariffCalculateUseCase;

    final Currency currency = new CurrencyFactory(code -> true).create("RUB");
    private CalculateResultPrice calculateResultPrice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        calculateResultPrice = new CalculateResultPrice(shipment, distance, tariffCalculateUseCase);
    }

    @ParameterizedTest(name = "Расчета итоговой цены {arguments}")
    @CsvSource({"200, 400, 350",
            "450,350,400",
            "1000, 400, 350"})
    void calcResultPrice(String amount, String cubicMeterAmount, String weightAmount) {

        Price cubicMeterPrice = new Price(new BigDecimal(cubicMeterAmount), currency);
        Price weightPrice = new Price(new BigDecimal(weightAmount), currency);
        Price basePrice = cubicMeterPrice.amount().compareTo(weightPrice.amount()) > 0 ? cubicMeterPrice : weightPrice;
        BigDecimal distanceInKm = new BigDecimal(amount);

        when(tariffCalculateUseCase.calcByCubicMeter(shipment)).thenReturn(cubicMeterPrice);
        when(tariffCalculateUseCase.calcByWeight(shipment)).thenReturn(weightPrice);
        when(distance.getDistanceInKilometers()).thenReturn(distanceInKm);

        BigDecimal expectedAmount = distance.getDistanceInKilometers().compareTo(new BigDecimal(450)) > 0 ?
                distanceInKm.divide(BigDecimal.valueOf(450), 2, RoundingMode.CEILING)
                        .multiply(basePrice.amount().setScale(2, RoundingMode.CEILING)) :
                basePrice.amount().setScale(2, RoundingMode.CEILING);

        Price expectedPrice = new Price(expectedAmount, cubicMeterPrice.currency());

        Price resultPrice = calculateResultPrice.getResulPrice();
        assertEquals(expectedPrice.amount().setScale(2, RoundingMode.CEILING), resultPrice.amount());
        assertEquals(expectedPrice.currency(), resultPrice.currency());
    }


}
