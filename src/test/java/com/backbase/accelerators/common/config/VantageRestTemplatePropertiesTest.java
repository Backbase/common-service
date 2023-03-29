package com.backbase.accelerators.common.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = VantageRestTemplatePropertiesTest.class)
class VantageRestTemplatePropertiesTest {

    private VantageRestTemplateProperties vantageConfigurationProperties;

    @BeforeEach
    void init() {
        vantageConfigurationProperties = new VantageRestTemplateProperties();
        vantageConfigurationProperties.setProxyPort("TestPort-8080");
        vantageConfigurationProperties.setProxyHost("TestHost");
    }

    @Test
    void testConfigurationProperties() {
        try {
            Assertions.assertNotNull(vantageConfigurationProperties.getProxyPort());
            Assertions.assertNotNull(vantageConfigurationProperties.getProxyHost());
        } catch (Exception e) {
            org.assertj.core.api.Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }
}
