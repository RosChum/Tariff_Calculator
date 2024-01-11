package ru.fastdelivery.presentation.api.request;

import jakarta.validation.constraints.NotNull;
import ru.fastdelivery.presentation.api.request.customValidator.CheckLatitudeRange;
import ru.fastdelivery.presentation.api.request.customValidator.CheckLongitudeRange;

import java.math.BigDecimal;


public record Departure(

        @CheckLatitudeRange(message = "Значение широты для координат отправления не находится в диапазоне координат!") @NotNull BigDecimal latitude,
        @CheckLongitudeRange(message = "Значение долготы для координат отправления не находится в диапазоне координат!") @NotNull BigDecimal longitude
) {


}
