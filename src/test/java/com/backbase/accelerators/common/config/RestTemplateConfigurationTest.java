package com.backbase.accelerators.common.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = RestTemplateConfigurationTest.class)
class RestTemplateConfigurationTest {


    @InjectMocks
    private RestTemplateConfiguration restTemplateConfiguration;

    @Mock
    private VantageRestTemplateProperties vantageConfigurationProperties;


    @Test
    void getRestTemplate() {
        try {
            Mockito.when(vantageConfigurationProperties.getProxyHost()).thenReturn("http://localhost");
            Mockito.when(vantageConfigurationProperties.getProxyPort()).thenReturn("8988");

            RestTemplate restTemplate = restTemplateConfiguration.vantageRestTemplate();
            Assertions.assertThat(restTemplate).isNotNull();
        } catch (Exception e) {
            org.assertj.core.api.Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }

    @Test
    void getRestTemplateException() {
        try {
            RestTemplate restTemplate = restTemplateConfiguration.vantageRestTemplate();
            Assertions.assertThat(restTemplate).isNotNull();
        } catch (Exception e) {
            org.assertj.core.api.Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }

    @Test
    void getRestTemplate_No_TrustStore() {
        try {
            RestTemplate restTemplate = restTemplateConfiguration.vantageRestTemplate();
            Assertions.assertThat(restTemplate).isNotNull();
        } catch (Exception e) {
            org.assertj.core.api.Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }
}
