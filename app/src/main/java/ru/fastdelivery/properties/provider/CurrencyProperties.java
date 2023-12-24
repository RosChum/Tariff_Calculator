package ru.fastdelivery.properties.provider;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.fastdelivery.domain.common.currency.CurrencyPropertiesProvider;

import java.util.List;

/**
 * Настройки валют из конфига
 */
@Configuration
@ConfigurationProperties("currency")
@Setter
@Slf4j
public class CurrencyProperties implements CurrencyPropertiesProvider {

    private List<String> available;

    @Override
    public boolean isAvailable(String code) {
        log.info("Create CurrencyProperties isAvailable");
        return available.contains(code);
    }
}
