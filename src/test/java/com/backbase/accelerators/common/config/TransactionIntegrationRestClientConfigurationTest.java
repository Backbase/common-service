package com.backbase.accelerators.common.config;


import com.backbase.transaction.integration.listener.client.v2.persistence.transactions.TransactionPresentationServiceApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
class TransactionIntegrationRestClientConfigurationTest {

    @InjectMocks
    private TransactionIntegrationRestClientConfiguration transactionIntegrationRestClientConfiguration;

    @Mock
    private RestTemplate restTemplate;


    @BeforeEach
    public void init() {

    }

    @Test
    void testCreateBean(){
        transactionIntegrationRestClientConfiguration.getScheme();
        transactionIntegrationRestClientConfiguration.setScheme("http");
        TransactionPresentationServiceApi transactionPresentationServiceApi = transactionIntegrationRestClientConfiguration.createTransactionInfoApiClient(restTemplate);
        Assertions.assertNotNull(transactionPresentationServiceApi);
    }
}
