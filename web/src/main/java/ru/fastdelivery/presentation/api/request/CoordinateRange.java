package ru.fastdelivery.presentation.api.request;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CoordinateRange {
    @Value(value = "${coordinate-range.latitudeMin}")
    private int latitudeMin ;
    @Value(value = "${coordinate-range.latitudeMax}")
    private int latitudeMax;
    @Value(value = "${coordinate-range.longitudeMin}")
    private int longitudeMin;
    @Value(value = "${coordinate-range.longitudeMax}")
    private int longitudeMax;
}
