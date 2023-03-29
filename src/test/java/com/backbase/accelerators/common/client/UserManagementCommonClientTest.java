package com.backbase.accelerators.common.client;

import com.backbase.dbs.user.manager.models.v2.GetIdentity;
import com.backbase.dbs.user.manager.models.v2.GetUser;
import com.backbase.dbs.user.manager.models.v2.GetUsersByLegalEntityIdsRequest;
import com.backbase.dbs.user.manager.models.v2.GetUsersList;
import com.backbase.dbs.usermanager.listener.client.v2.users.IdentityManagementApi;
import com.backbase.dbs.usermanager.listener.client.v2.users.UserManagementApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserManagementCommonClientTest.class)
class UserManagementCommonClientTest {
    @Spy
    @InjectMocks
    private UserManagementCommonClient userManagementClient;
    @Mock
    private UserManagementApi userManagerClient;
    @Mock
    private IdentityManagementApi identityManagementApi;

    @InjectMocks
    GetUser user;

    @InjectMocks
    GetIdentity identity;

    @InjectMocks
    GetUsersList usersList;


    @Test
    void testFindIdentityByExternalId(){
        Mockito.when(userManagerClient.getUserByExternalId(ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean())).thenReturn(user);
        Mockito.when(identityManagementApi.getIdentity(ArgumentMatchers.anyString())).thenReturn(identity);
        GetIdentity identity = userManagementClient.findIdentityByExternalId("jacksondusty");
        Assertions.assertNull(identity);
    }


    @Test
    void testFindUserByExternalId(){
        Mockito.when(userManagerClient.getUserByExternalId(ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean())).thenReturn(user);
        GetUser user = userManagementClient.findUserByExternalId("jacksondusty");
        Assertions.assertNotNull(user);
    }

    @Test
    void testFindUserByInternalId(){
        Mockito.when(userManagerClient.getUserById(ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean())).thenReturn(user);
        GetUser user = userManagementClient.findUserByInternalId("jacksondusty");
        Assertions.assertNotNull(user);
    }
    @Test
    void testFindUsers(){
        Mockito.when(userManagerClient.getUsersByLegalEntityIds(ArgumentMatchers.any(GetUsersByLegalEntityIdsRequest.class), ArgumentMatchers.anyBoolean())).thenReturn(usersList);
        GetUsersList userList = userManagementClient.findUsers(new GetUsersByLegalEntityIdsRequest() );
        Assertions.assertNotNull(userList);
    }

}
