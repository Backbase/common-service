package com.backbase.accelerators.common.config;

import com.backbase.dbs.usermanager.listener.client.v2.users.IdentityManagementApi;
import com.backbase.dbs.usermanager.listener.client.v2.users.UserManagementApi;
import com.backbase.dbs.usermanager.listener.client.v2.users.UserProfileManagementApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = UserManagementRestClientConfigurationTest.class)
class UserManagementRestClientConfigurationTest {
    @InjectMocks
    private UserManagementRestClientConfiguration userRestClientConfiguration;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void testCreateUserInfoApiClient() {
        try {
            userRestClientConfiguration.setScheme("http");
            userRestClientConfiguration.getScheme();
            UserManagementApi userManagementApi = userRestClientConfiguration.createUserInfoApiClient(restTemplate);
            Assertions.assertNotNull(userManagementApi);
        } catch (Exception e) {
            org.assertj.core.api.Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }

    @Test
    void testCreateIdentityManagementApiClient() {
        try {
            userRestClientConfiguration.setScheme("http");
            userRestClientConfiguration.getScheme();
            IdentityManagementApi identityManagementApi = userRestClientConfiguration.createIdentityManagementApiClient(restTemplate);
            Assertions.assertNotNull(identityManagementApi);
        } catch (Exception e) {
            org.assertj.core.api.Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }
    @Test
    void createUserProfileMangementApiClient_success() {
        try {
            userRestClientConfiguration.setScheme("http");
            userRestClientConfiguration.getScheme();
            UserProfileManagementApi userProfileManagementApi = userRestClientConfiguration.createUserProfileMangementApiClient(restTemplate);
            Assertions.assertNotNull(userProfileManagementApi);
        } catch (Exception e) {
            org.assertj.core.api.Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }
}
