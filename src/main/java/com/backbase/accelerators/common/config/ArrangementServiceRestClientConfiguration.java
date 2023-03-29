package com.backbase.accelerators.common.config;

import com.backbase.arrangement.integration.listener.client.v2.persistence.ApiClient;
import com.backbase.arrangement.integration.listener.client.v2.persistence.arrangements.ArrangementsApi;
import com.backbase.arrangement.integration.listener.client.v2.persistence.arrangements.ProductSummaryApi;
import com.backbase.arrangement.integration.listener.client.v2.persistence.arrangements.ProductsApi;
import com.backbase.buildingblocks.context.ContextScoped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@ContextScoped
@Configuration
public class ArrangementServiceRestClientConfiguration extends HttpConfig  {

    private static final String ARRANGEMENT_MANAGER_SERVICE_ID = "arrangement-manager";

    @Bean
    @ContextScoped
    public ArrangementsApi createArrangementApiClient(@Qualifier("interServiceRestTemplate") RestTemplate restTemplate) {
        var apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(scheme + "://" + ARRANGEMENT_MANAGER_SERVICE_ID);
        return new ArrangementsApi(apiClient);
    }


    @Bean
    @ContextScoped
    public ProductSummaryApi createProductSummaryApiClient(@Qualifier("interServiceRestTemplate") RestTemplate restTemplate) {
        var apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(scheme + "://" + ARRANGEMENT_MANAGER_SERVICE_ID);
        return new ProductSummaryApi(apiClient);
    }

    @Bean
    @ContextScoped
    public ProductsApi createProductsApiClient(@Qualifier("interServiceRestTemplate") RestTemplate restTemplate) {
        var apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(scheme + "://" + ARRANGEMENT_MANAGER_SERVICE_ID);
        return new ProductsApi(apiClient);
    }


}
