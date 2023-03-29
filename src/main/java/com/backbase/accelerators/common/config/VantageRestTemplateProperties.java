package com.backbase.accelerators.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "vantage")
public class VantageRestTemplateProperties {

    private String proxyHost;
    private String proxyPort;

}
