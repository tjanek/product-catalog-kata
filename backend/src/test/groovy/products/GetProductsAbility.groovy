package products

import org.springframework.http.ResponseEntity

trait GetProductsAbility extends ProductsHttpRequestAbility {

    ResponseEntity<Map> getProducts() {
        httpGetRequest("$PRODUCTS_CATALOG_V1_BASE_URL")
    }

}