package ru.fastdelivery.presentation.api.request;

import jakarta.validation.constraints.NotNull;
import ru.fastdelivery.presentation.api.request.customValidator.CheckLatitudeRange;
import ru.fastdelivery.presentation.api.request.customValidator.CheckLongitudeRange;

import java.math.BigDecimal;

public record Destination(
        @CheckLatitudeRange(message = "Значение широты для места назначения не находится в диапазоне координат!") @NotNull BigDecimal latitude,
        @CheckLongitudeRange(message = "Значение долготы для места назначения не находится в диапазоне координат!") @NotNull BigDecimal longitude
) {

}
