package ru.fastdelivery.domain.common.cubicMeter;

import java.math.BigDecimal;

public record CubicMeter(BigDecimal cubicMeter) {


    public CubicMeter add(CubicMeter cubicMeter){
        return new CubicMeter(this.cubicMeter.add(cubicMeter.cubicMeter));
    }
}
