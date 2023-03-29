package com.backbase.accelerators.common.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

import java.time.Duration;

@Slf4j
@Configuration
@AllArgsConstructor
public class WebClientConfiguration {

    private final VantageRestTemplateProperties vantageConfigurationProperties;
    private static final int DURATION_IN_SECONDS = 120;

    @Bean
    @Primary
    public WebClient vantageWebClient() {
        return WebClient
                .builder()
                .clientConnector(createHttpConnector())
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(100 * 1024 * 1024))
                        .build())
                .filters(this::callFunctions)
                .build();
    }

    protected void callFunctions(java.util.List<ExchangeFilterFunction> exchangeFilterFunctions) {
        if (log.isDebugEnabled()) {
            exchangeFilterFunctions.add(logRequest());
            exchangeFilterFunctions.add(logResponse());
        }
    }

    protected ReactorClientHttpConnector createHttpConnector() {
        HttpClient httpClient = null;
        if (StringUtils.isEmpty(vantageConfigurationProperties.getProxyHost())) {
            httpClient = HttpClient.create()
                    .responseTimeout(Duration.ofSeconds(DURATION_IN_SECONDS));
        } else {
            httpClient = HttpClient.create()
                    .proxy(proxy -> proxy
                            .type(ProxyProvider.Proxy.HTTP)
                            .host(vantageConfigurationProperties.getProxyHost())
                            .port(Integer.parseInt(vantageConfigurationProperties.getProxyPort())));
        }

        return new ReactorClientHttpConnector(httpClient);
    }


    ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(this ::logRequestBody);
    }

    Mono<ClientRequest> logRequestBody(ClientRequest clientRequest) {
        var sbHeader = new StringBuilder("WebClient Request Headers : \n");
        clientRequest.headers().forEach((name, values) -> values.forEach(value -> sbHeader.append("\t\t" + name + " : " + value + "\n")));
        log.debug(sbHeader.toString());
        var sbMethod = new StringBuilder("WebClient Request Method : \n");
        sbMethod.append(clientRequest.method().name());
        sbMethod.append(" to ");
        sbMethod.append(clientRequest.url());
        log.debug(sbMethod.toString());
        var sbBody = new StringBuilder("WebClient Request body : \n");
        sbBody.append(clientRequest.body());
        log.debug(sbBody.toString());
        return Mono.just(clientRequest);
    }

    public static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(response -> {
            logStatus(response);
            logHeaders(response);
            return logBody(response);
        });
    }


    private static void logStatus(ClientResponse response) {
        HttpStatus status = response.statusCode();
        log.debug("WeblClient Returned response status code {} ({})", status.value(), status.getReasonPhrase());
    }

    private static Mono<ClientResponse> logBody(ClientResponse response) {
        if (response.statusCode() != null && (response.statusCode().is4xxClientError() || response.statusCode().is5xxServerError())) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> {

                        log.debug("WeblClient Response Body {}", body);
                        return Mono.just(ClientResponse.create(response.statusCode())
                                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                                .body(body)
                                .build()); // normal

                    });
        }  else {
            return response.bodyToMono(String.class)
                    .flatMap(body -> {
                        String result = body.replaceAll("\\s+", " ");
                        log.debug("WeblClient Response Body : {} \n\n", result);
                        return Mono.just(ClientResponse.create(HttpStatus.OK)
                                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                                .body(body)
                                .build()); // normal
                    });
        }
    }

    private static void logHeaders(ClientResponse response) {
        log.debug("WeblClient Response Headers : ");
        response.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> log.debug("{} : {} ", name, value)
                )
        );
    }
}
