package ru.fastdelivery.presentation.api.request.customValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public class CheckLongitudeRangeValidator implements ConstraintValidator<CheckLongitudeRange, BigDecimal> {

    @Value(value = "${coordinate-range.longitudeMin}")
    private int longitudeMin;
    @Value(value = "${coordinate-range.longitudeMax}")
    private int longitudeMax;

    @Override
    public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext constraintValidatorContext) {
        return bigDecimal.compareTo(new BigDecimal(longitudeMin)) >= 0 && bigDecimal.compareTo(new BigDecimal(longitudeMax)) <= 0;
    }
}
