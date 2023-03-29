package com.backbase.accelerators.common.config;

import com.backbase.buildingblocks.context.ContextScoped;
import com.backbase.transaction.integration.listener.client.v2.persistence.ApiClient;
import com.backbase.transaction.integration.listener.client.v2.persistence.transactions.TransactionPresentationServiceApi;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;


@Getter
@Setter
@ContextScoped
@Configuration
public class TransactionIntegrationRestClientConfiguration extends HttpConfig {

    private static final String TRANSACTION_MANAGER_SERVICE_ID = "transaction-manager";

    @Bean
    @Primary
    @ContextScoped
    public TransactionPresentationServiceApi createTransactionInfoApiClient(@Qualifier("interServiceRestTemplate") RestTemplate restTemplate) {
        var apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(scheme + "://" + TRANSACTION_MANAGER_SERVICE_ID);
        return new TransactionPresentationServiceApi(apiClient);
    }


}
