package com.backbase.accelerators.common.client;

import com.backbase.dbs.accessgroups.listener.client.v2.LegalEntitiesApi;
import com.backbase.dbs.accessgroups.models.v2.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LegalEntityClient {
    @Qualifier("createLegalEntitiesApi")
    private final LegalEntitiesApi legalEntitiesApi;

    public LegalEntityItem findLegalEntityById(String legalEntityId) {
        log.debug("Getting User Info Based on Internal Id.");
        return legalEntitiesApi.getLegalEntityById(legalEntityId);
    }

    public LegalEntityItemBase findLegalEntityByExternalId(String legalEntityExternalId){
        log.debug("Getting User Info Based on external Id.");
        return legalEntitiesApi.getLegalEntityByExternalId(legalEntityExternalId);
    }

    public GetServiceAgreement findServiceAgreementByExternalId(String legalEntityExternalId) {
        log.debug("Getting User Info Based on external Id.");
        return legalEntitiesApi.getMasterServiceAgreementByExternalLegalEntity(legalEntityExternalId);

    }

   public  List<BatchResponseItem> updateLegalEntities(List<LegalEntityPut> legalEntityPut){
       log.debug("Updating legal entities");
       return legalEntitiesApi.putLegalEntities(legalEntityPut);
   }
}
