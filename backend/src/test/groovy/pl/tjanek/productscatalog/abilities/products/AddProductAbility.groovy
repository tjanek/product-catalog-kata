package pl.tjanek.productscatalog.abilities.products

import org.springframework.http.ResponseEntity

trait AddProductAbility extends ProductsHttpRequestAbility {

    ResponseEntity<Map> addProductToCatalog(String name, String initialPrice) {

        httpPostRequest(PRODUCTS_CATALOG_V1_BASE_URL, [
                name: name,
                initialPrice: initialPrice
        ])
    }

}