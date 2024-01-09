package ru.fastdelivery.presentation.api.request;

import java.math.BigDecimal;

public record Destination (
        BigDecimal latitude,
        BigDecimal longitude
) {

}
