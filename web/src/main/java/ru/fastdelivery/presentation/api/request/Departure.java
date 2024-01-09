package ru.fastdelivery.presentation.api.request;

import javax.validation.constraints.Size;
import java.math.BigDecimal;


public record Departure(
        BigDecimal latitude
        , @Size(min = 35, max = 40, message = "Значение не находится в диапазоне координат!!!") BigDecimal longitude
) {

}
