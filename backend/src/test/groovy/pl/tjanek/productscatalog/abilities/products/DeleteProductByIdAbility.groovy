package pl.tjanek.productscatalog.abilities.products

import org.springframework.http.ResponseEntity

trait DeleteProductByIdAbility extends ProductsHttpRequestAbility {

    ResponseEntity<Map> deleteProductById(String id) {
        httpDeleteRequest("$PRODUCTS_CATALOG_V1_BASE_URL/$id")
    }

}