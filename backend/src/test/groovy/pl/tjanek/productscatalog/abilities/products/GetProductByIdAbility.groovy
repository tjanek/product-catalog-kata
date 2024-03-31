package pl.tjanek.productscatalog.abilities.products

import org.springframework.http.ResponseEntity

trait GetProductByIdAbility extends ProductsHttpRequestAbility {

    ResponseEntity<Map> getProduct(String id) {
        httpGetRequest("$PRODUCTS_CATALOG_V1_BASE_URL/$id")
    }

}