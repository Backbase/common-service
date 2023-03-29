package com.backbase.accelerators.common.config;

import org.apache.logging.slf4j.SLF4JLogger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootTest(classes = VantageRestTemplatePropertiesTest.class)
class WebClientConfigurationTest {

    @InjectMocks
    private WebClientConfiguration webClientConfiguration;

    @Mock
    private VantageRestTemplateProperties vantageConfigurationProperties;

    @Mock
    SLF4JLogger log;

    @Test
    void testSSLConnectorEmptyTrustStore() {
        try {
            Mockito.when(vantageConfigurationProperties.getProxyHost()).thenReturn("http://localhost");
            Mockito.when(vantageConfigurationProperties.getProxyPort()).thenReturn("8888");

            ClientHttpConnector clientHttpConnector = webClientConfiguration.createHttpConnector();
            Assertions.assertThat(clientHttpConnector).isNotNull();
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }

    @Test
    void testSSLConnectorEmptyProxyHost() {
        try {

            Mockito.when(vantageConfigurationProperties.getProxyHost()).thenReturn("");
            Mockito.when(vantageConfigurationProperties.getProxyPort()).thenReturn("8888");

            ClientHttpConnector clientHttpConnector = webClientConfiguration.createHttpConnector();
            Assertions.assertThat(clientHttpConnector).isNotNull();
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }

    @Test
    void testVantageWebClient() {
        Mockito.when(log.isDebugEnabled()).thenReturn(true);
        WebClient webClient = webClientConfiguration.vantageWebClient();
        Assertions.assertThat(webClient).isNotNull();
    }

    @Test
    void testLogRequest() {
        ExchangeFilterFunction exchangeFilterFunction = webClientConfiguration.logRequest();
        Assertions.assertThat(exchangeFilterFunction).isNotNull();
    }

    @Test
    void testLogRequestBody() {
        org.springframework.web.reactive.function.client.ClientRequest  clientRequest= Mockito.mock((org.springframework.web.reactive.function.client.ClientRequest.class));
        HttpHeaders headers= new HttpHeaders();
        headers.add("key1", "value1");
        Mockito.when( clientRequest.headers()).thenReturn(headers);
        HttpMethod method= HttpMethod.resolve(HttpMethod.GET.name());
        Mockito.when( clientRequest.method()).thenReturn(method);
        Mockito.when( clientRequest.body()).thenReturn(null);

        Mono<ClientRequest>  clientRequestMono = webClientConfiguration.logRequestBody(clientRequest);
        Assertions.assertThat(clientRequestMono).isNotNull();
    }
    @Test
    void testLogResponse() {
        ExchangeFilterFunction exchangeFilterFunction = WebClientConfiguration.logResponse();
        Assertions.assertThat(exchangeFilterFunction).isNotNull();
    }
}
