package com.backbase.accelerators.common.util;


import com.backbase.dbs.user.manager.models.v2.GetIdentity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
@ExtendWith(SpringExtension.class)
 class JsonUtilTest {

    @InjectMocks
    JsonUtil jsonUtil;

    @Test
    void convertObjectToJson_success()
    {
        GetIdentity identity = new GetIdentity().fullName("abc").mobileNumber("1231234567");
        System.out.println("..."+ jsonUtil.convertObjectToJson(identity));
        Assertions.assertNotNull(jsonUtil.convertObjectToJson(identity));
    }

    @Test
    void convertObjectToJson_sexception()
    {
        GetIdentity identity = new GetIdentity();
        Assertions.assertNotNull(jsonUtil.convertObjectToJson(identity));
    }

    @Test
    void readJsonfileToObject_success() throws IOException {
        GetIdentity identity = new GetIdentity().fullName("abc").mobileNumber("1231234567");

        jsonUtil.readJsonfileToObject("json/getIdentity.json",GetIdentity.class);
        Assertions.assertNotNull(jsonUtil.convertObjectToJson(identity));
    }


}
