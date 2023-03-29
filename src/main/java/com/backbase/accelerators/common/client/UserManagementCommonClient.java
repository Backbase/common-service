package com.backbase.accelerators.common.client;

import com.backbase.dbs.user.manager.models.v2.GetIdentity;
import com.backbase.dbs.user.manager.models.v2.GetUser;
import com.backbase.dbs.user.manager.models.v2.GetUsersByLegalEntityIdsRequest;
import com.backbase.dbs.user.manager.models.v2.GetUsersList;
import com.backbase.dbs.usermanager.listener.client.v2.users.IdentityManagementApi;
import com.backbase.dbs.usermanager.listener.client.v2.users.UserManagementApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserManagementCommonClient {
    @Qualifier("createUserInfoApiClient")
    private final UserManagementApi userManagerClient;

    @Qualifier("createIdentityManagementApiClient")
    private final IdentityManagementApi identityManagementApi;

    public GetUser findUserByExternalId(String externalId) {
        log.debug("Getting User Info Based on external Id.");
        return userManagerClient.getUserByExternalId(externalId, Boolean.TRUE);

    }

    public GetUser findUserByInternalId(String externalId) {
        log.debug("Getting User Info Based on external Id.");
        return userManagerClient.getUserById(externalId, Boolean.TRUE);

    }
    public GetUsersList findUsers(GetUsersByLegalEntityIdsRequest getUsersByLegalEntityIdsRequest){
        return userManagerClient.getUsersByLegalEntityIds(getUsersByLegalEntityIdsRequest,Boolean.TRUE);
    }

    public GetIdentity findIdentityByExternalId(String userExternalId) {
        GetUser user = this.findUserByExternalId(userExternalId);
        log.debug("Getting User Info from Identity.");
        return identityManagementApi.getIdentity(user.getId());
    }


}
