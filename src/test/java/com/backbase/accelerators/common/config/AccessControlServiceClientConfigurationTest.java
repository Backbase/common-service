package com.backbase.accelerators.common.config;

import com.backbase.dbs.accessgroups.listener.client.v2.LegalEntitiesApi;
//import com.backbase.dbs.accessgroups.listener.client.v2.LegalEntityApi;
import com.backbase.dbs.accessgroups.listener.client.v2.UsersApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = AccessControlServiceClientConfigurationTest.class)
class AccessControlServiceClientConfigurationTest {
    @InjectMocks
    private AccessControlServiceClientConfiguration accessControlClientConfiguration;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void createBean() {
        try {
            accessControlClientConfiguration.setScheme("http");
            accessControlClientConfiguration.getScheme();
            LegalEntitiesApi legalEntitiesApi = accessControlClientConfiguration.createLegalEntitiesApi(restTemplate);
            Assertions.assertNotNull(legalEntitiesApi);
        } catch (Exception e) {
            org.assertj.core.api.Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }

    @Test
    void createLegalEntitiesApiBean() {
        try {
            accessControlClientConfiguration.setScheme("http");
            accessControlClientConfiguration.getScheme();
            LegalEntitiesApi legalEntityApi = accessControlClientConfiguration.createLegalEntitiesApi(restTemplate);
            Assertions.assertNotNull(legalEntityApi);
        } catch (Exception e) {
            org.assertj.core.api.Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }
    @Test
    void createUsersApiBean() {
        try {
            accessControlClientConfiguration.setScheme("http");
            accessControlClientConfiguration.getScheme();
            UsersApi usersApi = accessControlClientConfiguration.createUsersApi(restTemplate);
            Assertions.assertNotNull(usersApi);
        } catch (Exception e) {
            org.assertj.core.api.Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }
}
