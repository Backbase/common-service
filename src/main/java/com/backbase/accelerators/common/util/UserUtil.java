package com.backbase.accelerators.common.util;

import com.backbase.dbs.accessgroups.models.v2.LegalEntityItem;
import com.backbase.dbs.user.manager.models.v2.GetIdentity;
import com.backbase.accelerators.common.client.LegalEntityClient;
import com.backbase.accelerators.common.client.UserManagementCommonClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserManagementCommonClient userManagementCommonClient;

    private final LegalEntityClient legalEntityClient;

    private static final String ENTITY_TYPE = "entityType";
    private static final String RETAIL = "retail";

    public boolean isUserRetail(String userId) {
        boolean isRetail = false;
        GetIdentity identity = userManagementCommonClient.findIdentityByExternalId(userId);
        if (identity != null) {
            LegalEntityItem legalEntityItem = legalEntityClient.findLegalEntityById(identity.getLegalEntityInternalId());
            isRetail = getRetailAttributeFromLegalEntity(legalEntityItem);
            if (!isRetail) {
                Map<String, String> identityAttributes = identity.getAttributes();
                if (identityAttributes != null && identityAttributes.containsKey(ENTITY_TYPE)) {
                    log.debug("Found the entity type");
                    String retailuser = identityAttributes.get(ENTITY_TYPE);
                    if ((StringUtils.isNotEmpty(retailuser)) && (RETAIL.equalsIgnoreCase(retailuser))) {
                        log.debug("Found the entity type and the value is Retail");
                        isRetail = true;
                    }
                }
            }
        }
        return isRetail;
    }

    private boolean getRetailAttributeFromLegalEntity(LegalEntityItem legalEntityItem) {
        boolean isRetail = false;
        if (legalEntityItem != null) {
            Map<String, String> legalEntityAdditions = legalEntityItem.getAdditions();
            if (legalEntityAdditions != null && legalEntityAdditions.containsKey(ENTITY_TYPE)) {
                String retailuser = legalEntityAdditions.get(ENTITY_TYPE);
                if (RETAIL.equalsIgnoreCase(retailuser)) {
                    isRetail = true;
                }
            }
        }
        return isRetail;
    }
}
