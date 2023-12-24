package ru.fastdelivery.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.currency.CurrencyPropertiesProvider;
import ru.fastdelivery.usecase.TariffCalculateUseCase;
import ru.fastdelivery.usecase.WeightPriceProvider;

/**
 * Определение реализаций бинов для всех модулей приложения
 */
@Configuration
@Slf4j
public class Beans {

    @Bean
    public CurrencyFactory currencyFactory(CurrencyPropertiesProvider currencyProperties) {
        log.info("Create CurrencyFactory");
        return new CurrencyFactory(currencyProperties);
    }

    @Bean
    public TariffCalculateUseCase tariffCalculateUseCase(WeightPriceProvider weightPriceProvider) {
        log.info("Create TariffCalculateUseCase");
        return new TariffCalculateUseCase(weightPriceProvider);
    }
}
