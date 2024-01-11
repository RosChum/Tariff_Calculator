package ru.fastdelivery.presentation.api.request.customValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public class CheckLatitudeRangeValidator implements ConstraintValidator<CheckLatitudeRange, BigDecimal> {

    @Value(value = "${coordinate-range.latitudeMin}")
    private int latitudeMin;
    @Value(value = "${coordinate-range.latitudeMax}")
    private int latitudeMax;

    @Override
    public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext constraintValidatorContext) {
        return bigDecimal.compareTo(new BigDecimal(latitudeMin)) >= 0 && bigDecimal.compareTo(new BigDecimal(latitudeMax)) <= 0;
    }

}
