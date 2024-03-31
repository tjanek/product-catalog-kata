package pl.tjanek.productscatalog.assertions.products

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pl.tjanek.productscatalog.assertions.CommonAssertion

class RemoveProductsFromCatalogAssertion extends CommonAssertion {

    private final ResponseEntity<Map> result

    static RemoveProductsFromCatalogAssertion assertThatRemovedProductsFromCatalog(ResponseEntity<Map> response) {
        return new RemoveProductsFromCatalogAssertion(response)
    }

    private RemoveProductsFromCatalogAssertion(ResponseEntity<Map> response) {
        this.result = response
    }

    RemoveProductsFromCatalogAssertion wasRemoved() {
        assert result.statusCode == HttpStatus.OK
        this
    }

    RemoveProductsFromCatalogAssertion wasNotFound() {
        assert result.statusCode == HttpStatus.NOT_FOUND
        this
    }
}
