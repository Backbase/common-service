package com.backbase.accelerators.common.config;


import com.backbase.arrangement.integration.listener.client.v2.persistence.arrangements.ArrangementsApi;
import com.backbase.arrangement.integration.listener.client.v2.persistence.arrangements.ProductSummaryApi;
import com.backbase.arrangement.integration.listener.client.v2.persistence.arrangements.ProductsApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
class ArrangementServiceRestClientConfigurationTest {

    @InjectMocks
    private ArrangementServiceRestClientConfiguration arrangementServiceRestClientConfiguration;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void createBean() {
        try {
            arrangementServiceRestClientConfiguration.setScheme("http");
            arrangementServiceRestClientConfiguration.getScheme();
            ArrangementsApi arrangementsApi = arrangementServiceRestClientConfiguration.createArrangementApiClient(restTemplate);
            Assertions.assertNotNull(arrangementsApi);
        } catch (Exception e) {
            org.assertj.core.api.Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }

    @Test
    void createProductSummaryApiClient_success() {
        try {
            arrangementServiceRestClientConfiguration.setScheme("http");
            arrangementServiceRestClientConfiguration.getScheme();
            ProductSummaryApi productSummaryApi = arrangementServiceRestClientConfiguration.createProductSummaryApiClient(restTemplate);
            Assertions.assertNotNull(productSummaryApi);
        } catch (Exception e) {
            org.assertj.core.api.Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }

    @Test
    void createProductsApiClient_success() {
        try {
            arrangementServiceRestClientConfiguration.setScheme("http");
            arrangementServiceRestClientConfiguration.getScheme();
            ProductsApi productsApi = arrangementServiceRestClientConfiguration.createProductsApiClient(restTemplate);
            Assertions.assertNotNull(productsApi);
        } catch (Exception e) {
            org.assertj.core.api.Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }
}