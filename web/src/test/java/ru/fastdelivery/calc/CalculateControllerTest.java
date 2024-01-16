package ru.fastdelivery.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.fastdelivery.ControllerTest;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;
import ru.fastdelivery.domain.delivery.shippingDistance.Distance;
import ru.fastdelivery.presentation.api.request.CalculatePackagesRequest;
import ru.fastdelivery.presentation.api.request.CargoPackage;
import ru.fastdelivery.presentation.api.request.Departure;
import ru.fastdelivery.presentation.api.request.Destination;
import ru.fastdelivery.presentation.api.response.CalculatePackagesResponse;
import ru.fastdelivery.usecase.CalculateResultPrice;
import ru.fastdelivery.usecase.TariffCalculateUseCase;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CalculateControllerTest extends ControllerTest {

    final String baseCalculateApi = "/api/v1/calculate/";
    @MockBean
    TariffCalculateUseCase useCase;
    @MockBean
    CurrencyFactory currencyFactory;

    @Mock
    Distance distance;
    @Mock
    CalculateResultPrice calculateResultPrice;
    @Mock
    Shipment shipment;
    @Mock
    Price price;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        calculateResultPrice = new CalculateResultPrice(shipment, distance, useCase);

    }


    @Test
    @DisplayName("Валидные данные для расчета стоимость -> Ответ 200")
    void whenValidInputData_thenReturn200() {
        var request = new CalculatePackagesRequest(
                List.of(new CargoPackage(BigInteger.TEN, BigInteger.TEN, BigInteger.TEN, BigInteger.TEN)), "RUB",
                new Destination(new BigDecimal("56.330889"), new BigDecimal("44.002981")),
                new Departure(new BigDecimal("55.752184"), new BigDecimal("37.608970")));

        var rub = new CurrencyFactory(code -> true).create("RUB");
        when(useCase.calcByWeight(any())).thenReturn(new Price(BigDecimal.valueOf(10), rub));
        when(useCase.calcByCubicMeter(any())).thenReturn(new Price(BigDecimal.valueOf(10), rub));
        when(useCase.minimalPrice()).thenReturn(new Price(BigDecimal.valueOf(5), rub));
        when(distance.getDistanceInKilometers()).thenReturn(new BigDecimal("1000"));
        when(price.getNewPrice(any())).thenReturn(new Price(new BigDecimal(10), rub));
//
//        when(calculateResultPrice.getResulPrice()).thenReturn( new Price(new BigDecimal(10), rub));
        ResponseEntity<CalculatePackagesResponse> response =
                restTemplate.postForEntity(baseCalculateApi, request, CalculatePackagesResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Список упаковок == null -> Ответ 400")
    void whenEmptyListPackages_thenReturn400() {
        var request = new CalculatePackagesRequest(null, "RUB", new Destination(new BigDecimal("56.330889"), new BigDecimal("44.002981")),
                new Departure(new BigDecimal("55.752184"), new BigDecimal("37.608970")));

        ResponseEntity<String> response = restTemplate.postForEntity(baseCalculateApi, request, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
