package assertions.products

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ProductInCatalogAssertion {

    private final ResponseEntity<Map> result

    static ProductInCatalogAssertion assertThatProductInCatalog(ResponseEntity<Map> response) {
        return new ProductInCatalogAssertion(response)
    }

    private ProductInCatalogAssertion(ResponseEntity<Map> response) {
        this.result = response
    }

    ProductInCatalogAssertion isInCatalog() {
        assert result.statusCode == HttpStatus.OK
        this
    }

    ProductInCatalogAssertion isNotInCatalog() {
        assert result.statusCode == HttpStatus.NOT_FOUND
        this
    }

    ProductInCatalogAssertion hasName(String value) {
        assert result.body.name == value
        this
    }

    ProductInCatalogAssertion hasPrice(String value) {
        assert result.body.price as String == value
        this
    }

}
