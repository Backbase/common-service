package com.backbase.accelerators.common.util;

import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import com.backbase.pandp.arrangement.query.rest.spec.v2.arrangements.AccountProductItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = GsonUtilTest.class)
class GsonUtilTest {

    @InjectMocks
    GsonUtil gsonUtil;

    @Test
    void convertObjectToJson_success() {
        AccountProductItem identity = new AccountProductItem().externalId("12345678")
                .externalProductId("productId")
                .externalProductTypeId("kind2");
        System.out.println("..." + gsonUtil.convertObjectToJson(identity));
        Assertions.assertNotNull(gsonUtil.convertObjectToJson(identity));
    }

    @Test
    void readJsonfileToObject_success() throws IOException {

        AccountProductItem item = gsonUtil.readJsonFileToObject(AccountProductItem.class, "json/accountProductItem.json");
        Assertions.assertNotNull(item);
    }

    @Test
    void readJsonFileToString_success() throws IOException {

        Assertions.assertNotNull(gsonUtil.readJsonFileToString("json/accountProductItem.json"));
    }

    @Test
    void readJsonFileToString__exception() throws IOException {
        try {
            gsonUtil.readJsonFileToString("json/accountProductItemn.json");
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), InternalServerErrorException.class);
        }
    }


    @Test
    void readJsonFileToObjectList_success() throws IOException {

        List<AccountProductItem> items = new ArrayList<>();
        Assertions.assertNotNull(gsonUtil.readJsonFileToObjectList(items.getClass(), "json/accountProductItemList.json"));
    }
}
