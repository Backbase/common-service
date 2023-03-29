package com.backbase.accelerators.common.client;

import com.backbase.arrangement.integration.listener.client.v2.persistence.arrangements.ProductsApi;
import com.backbase.pandp.arrangement.query.rest.spec.v2.arrangements.AccountProductItem;
import com.backbase.pandp.arrangement.query.rest.spec.v2.arrangements.AccountSchemasProductItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductClient {


    @Qualifier("createProductsApiClient")
    private final ProductsApi productsApi;


    public AccountProductItem getProduct(String productId) {
        log.debug("Getting User Info Based on external Id.");
        return productsApi.getProductById(productId);
    }


    public List<AccountSchemasProductItem> getProducts() {
        log.debug("Getting User Info Based on external Id.");
        return productsApi.getProducts(null,null);
    }
}
