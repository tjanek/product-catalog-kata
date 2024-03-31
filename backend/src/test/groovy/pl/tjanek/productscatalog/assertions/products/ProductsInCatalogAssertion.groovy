package pl.tjanek.productscatalog.assertions.products

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pl.tjanek.productscatalog.assertions.CommonAssertion

class ProductsInCatalogAssertion extends CommonAssertion {

    private final ResponseEntity<Map> result

    static ProductsInCatalogAssertion assertThatProductsInCatalog(ResponseEntity<Map> response) {
        return new ProductsInCatalogAssertion(response)
    }

    private ProductsInCatalogAssertion(ResponseEntity<Map> response) {
        this.result = response
    }

    ProductsInCatalogAssertion hasProduct(String id, String name, String price) {
        assert result.statusCode == HttpStatus.OK
        assert result.body.products.find {
            it.id == id &&
            it.name == name &&
            it.price as String == price
        } != null
        this
    }

}
