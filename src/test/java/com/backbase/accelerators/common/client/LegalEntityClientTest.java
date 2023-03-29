package com.backbase.accelerators.common.client;

import com.backbase.dbs.accessgroups.listener.client.v2.LegalEntitiesApi;
import com.backbase.dbs.accessgroups.models.v2.GetServiceAgreement;
import com.backbase.dbs.accessgroups.models.v2.LegalEntityItem;
import com.backbase.dbs.accessgroups.models.v2.LegalEntityItemBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = LegalEntityClientTest.class)
class LegalEntityClientTest {
    @InjectMocks
    private LegalEntityClient legalEntityClient;
    @Mock
    private LegalEntitiesApi legalEntitiesApi;

    @Test
    void testFindLegalEntityById(){
        try {
            LegalEntityItem legalEntityItem = new LegalEntityItem();
            Mockito.when(legalEntitiesApi.getLegalEntityById(ArgumentMatchers.anyString())).thenReturn(legalEntityItem);
            legalEntityItem = legalEntityClient.findLegalEntityById("jacksondusty");
            Assertions.assertNotNull(legalEntityItem);
        } catch (Exception e) {
            org.assertj.core.api.Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }

    @Test
    void testFindLegalEntityByExternalId(){
        try {
            LegalEntityItemBase legalEntityItembase = new LegalEntityItemBase();
            Mockito.when(legalEntitiesApi.getLegalEntityByExternalId(ArgumentMatchers.anyString())).thenReturn(legalEntityItembase);
            legalEntityItembase = legalEntityClient.findLegalEntityByExternalId("cdiaz");
            Assertions.assertNotNull(legalEntityItembase);
        } catch (Exception e) {
            org.assertj.core.api.Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }


    @Test
    void testFindServiceAgreementByExternalId(){
        try {
            GetServiceAgreement getServiceAgreement = new GetServiceAgreement();
            Mockito.when(legalEntitiesApi.getMasterServiceAgreementByExternalLegalEntity(ArgumentMatchers.anyString())).thenReturn(getServiceAgreement);
            getServiceAgreement = legalEntityClient.findServiceAgreementByExternalId("cdiaz");
            Assertions.assertNotNull(getServiceAgreement);
        } catch (Exception e) {
            org.assertj.core.api.Assertions.assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }


}
