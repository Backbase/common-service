package com.backbase.accelerators.common.config;

import com.backbase.buildingblocks.context.ContextScoped;
import com.backbase.dbs.usermanager.listener.client.v2.ApiClient;
import com.backbase.dbs.usermanager.listener.client.v2.users.IdentityManagementApi;
import com.backbase.dbs.usermanager.listener.client.v2.users.UserManagementApi;
import com.backbase.dbs.usermanager.listener.client.v2.users.UserProfileManagementApi;
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
public class UserManagementRestClientConfiguration extends HttpConfig  {

    private static final String USER_MANAGER_SERVICE_ID = "user-manager";

    @Bean
    @ContextScoped
    public UserManagementApi createUserInfoApiClient(@Qualifier("interServiceRestTemplate") RestTemplate restTemplate) {
        var apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(scheme + "://" + USER_MANAGER_SERVICE_ID);
        return new UserManagementApi(apiClient);
    }

    @Bean
    @ContextScoped
    public IdentityManagementApi createIdentityManagementApiClient(@Qualifier("interServiceRestTemplate") RestTemplate restTemplate) {
        var apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(scheme + "://" + USER_MANAGER_SERVICE_ID);
        return new IdentityManagementApi(apiClient);
    }

    @Bean
    @ContextScoped
    public UserProfileManagementApi createUserProfileMangementApiClient(@Qualifier("interServiceRestTemplate") RestTemplate restTemplate) {
        var apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(scheme + "://" + USER_MANAGER_SERVICE_ID);
        return new UserProfileManagementApi(apiClient);
    }
}
