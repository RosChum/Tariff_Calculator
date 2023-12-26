package ru.fastdelivery.presentation.calc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.height.Height;
import ru.fastdelivery.domain.common.length.Length;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.common.width.Width;
import ru.fastdelivery.domain.delivery.pack.Pack;
import ru.fastdelivery.domain.delivery.shipment.Shipment;
import ru.fastdelivery.presentation.api.request.CalculatePackagesRequest;
import ru.fastdelivery.presentation.api.response.CalculatePackagesResponse;
import ru.fastdelivery.usecase.TariffCalculateUseCase;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/calculate/")
@RequiredArgsConstructor
@Tag(name = "Расчеты стоимости доставки")
@Slf4j
public class CalculateController {
    private final TariffCalculateUseCase tariffCalculateUseCase;
    private final CurrencyFactory currencyFactory;

    @PostMapping
    @Operation(summary = "Расчет стоимости по упаковкам груза")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    public CalculatePackagesResponse calculate(
            @Valid @RequestBody CalculatePackagesRequest request) {
        log.info("CalculatePackagesResponse " + request);
        var packsWeights = request.packages().stream().map(rec -> {
            return new Pack(new Weight(rec.weight()), new Height(rec.height())
                    , new Length(rec.length()), new Width(rec.width()));

        }).collect(Collectors.toList());

//                .map(CargoPackage::weight)
//                .map(Weight::new)
//                .map(Pack::new)
//                .toList();

        var shipment = new Shipment(packsWeights, currencyFactory.create(request.currencyCode()));
//        var calculatedPrice = tariffCalculateUseCase.calcByWeight(shipment);
//
        var minimalPrice = tariffCalculateUseCase.minimalPrice();


        return new CalculatePackagesResponse(getResulPrice(shipment), minimalPrice);
    }

    private Price getResulPrice(Shipment shipment) {
        Price priceByCubicMeter = tariffCalculateUseCase.calcByCubicMeter(shipment);
        Price priceByWeight = tariffCalculateUseCase.calcByWeight(shipment);

        log.info(" getResulPrice " + " priceByCubicMeter " + priceByCubicMeter.amount() + " priceByWeight " + priceByWeight.amount());

        return priceByCubicMeter.amount().compareTo(priceByWeight.amount()) < 0 ? priceByWeight : priceByCubicMeter;


    }
}

