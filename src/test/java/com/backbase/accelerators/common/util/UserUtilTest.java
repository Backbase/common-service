package com.backbase.accelerators.common.util;


import com.backbase.accelerators.common.client.LegalEntityClient;
import com.backbase.accelerators.common.client.UserManagementCommonClient;
import com.backbase.dbs.accessgroups.models.v2.LegalEntityItem;
import com.backbase.dbs.user.manager.models.v2.GetIdentity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;


@ExtendWith(SpringExtension.class)
class UserUtilTest {

    @InjectMocks
    UserUtil userUtil;

    @Mock
    UserManagementCommonClient userManagementCommonClient;

    @Mock
    LegalEntityClient legalEntityClient;

    @Test
    void isUserRetail_success() {
        GetIdentity identity = new GetIdentity().fullName("fullname");
        LegalEntityItem legalEntityItem = new LegalEntityItem();
        Map<String, String> additions = new HashMap<>();
        additions.put("entityType", "retail");
        legalEntityItem.setAdditions(additions);
        Mockito.when(userManagementCommonClient.findIdentityByExternalId(Mockito.anyString())).thenReturn(identity);
        Mockito.when(legalEntityClient.findLegalEntityById(Mockito.any())).thenReturn(legalEntityItem);
        boolean isUserRetail = userUtil.isUserRetail("userId");
        Assertions.assertEquals(Boolean.TRUE, isUserRetail);

        additions.put("entityType", "business");
        legalEntityItem.setAdditions(additions);
        isUserRetail = userUtil.isUserRetail("userId");
        Assertions.assertEquals(Boolean.FALSE, isUserRetail);
    }

    @Test
    void isUserRetail_identityAttribute_success() {
        GetIdentity identity = new GetIdentity().fullName("fullname");
        LegalEntityItem legalEntityItem = new LegalEntityItem();
        Mockito.when(userManagementCommonClient.findIdentityByExternalId(Mockito.anyString())).thenReturn(identity);
        Mockito.when(legalEntityClient.findLegalEntityById(Mockito.any())).thenReturn(legalEntityItem);
        Map<String, String> additions = new HashMap<>();
        additions.put("entityType", "retail");
        identity.setAttributes(additions);
        boolean isUserRetail = userUtil.isUserRetail("userId");
        Assertions.assertEquals(Boolean.TRUE, isUserRetail);
    }

}
