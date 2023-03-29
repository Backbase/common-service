package com.backbase.accelerators.common.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
@AllArgsConstructor
public class RestTemplateConfiguration {

    private final VantageRestTemplateProperties vantageConfigurationProperties;

    @Bean
    @Primary
    public RestTemplate vantageRestTemplate() {
        return new RestTemplate(getRequestFactory());
    }

    private HttpComponentsClientHttpRequestFactory getRequestFactory() {

        HttpClient client = null;
        try {
            var builder = HttpClientBuilder.create();
            if (!StringUtils.isEmpty(vantageConfigurationProperties.getProxyHost())) {
                builder.setProxy(new HttpHost(
                    vantageConfigurationProperties.getProxyHost(),
                    Integer.parseInt(vantageConfigurationProperties.getProxyPort())
                ));
            }
            client = builder.build();

        } catch (Exception e) {
            log.error("Error creating http client", e);
            client = HttpClients.createDefault();
        }

        return new HttpComponentsClientHttpRequestFactory(client);
    }


}
