package com.backbase.accelerators.common.config;

import com.backbase.buildingblocks.context.ContextScoped;
import com.backbase.dbs.accessgroups.listener.client.ApiClient;
import com.backbase.dbs.accessgroups.listener.client.v2.LegalEntitiesApi;
import com.backbase.dbs.accessgroups.listener.client.v2.UsersApi;
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
public class AccessControlServiceClientConfiguration extends HttpConfig {

    private static final String ACCESS_CONTROL_SERVICE_ID = "access-control";

    @Bean
    @ContextScoped
    public LegalEntitiesApi createLegalEntitiesApi(@Qualifier("interServiceRestTemplate") RestTemplate restTemplate) {
        var apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(scheme + "://" + ACCESS_CONTROL_SERVICE_ID);
        return new LegalEntitiesApi(apiClient);
    }


    @Bean
    @ContextScoped
    public UsersApi createUsersApi(@Qualifier("interServiceRestTemplate") RestTemplate restTemplate) {
        var apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(scheme + "://" + ACCESS_CONTROL_SERVICE_ID);
        return new UsersApi(apiClient);
    }

}
