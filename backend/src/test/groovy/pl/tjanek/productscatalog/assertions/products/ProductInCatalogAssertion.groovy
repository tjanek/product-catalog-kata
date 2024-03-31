package pl.tjanek.productscatalog.assertions.products

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pl.tjanek.productscatalog.assertions.CommonAssertion

class ProductInCatalogAssertion extends CommonAssertion {

    private final ResponseEntity<Map> result

    static ProductInCatalogAssertion assertThatProductInCatalog(ResponseEntity<Map> response) {
        return new ProductInCatalogAssertion(response)
    }

    private ProductInCatalogAssertion(ResponseEntity<Map> response) {
        this.result = response
    }

    ProductInCatalogAssertion isInCatalog() {
        assert result.statusCode == HttpStatus.OK
        assertThatIsUUID(result.body.id as String)
        this
    }

    ProductInCatalogAssertion wasAddedToCatalog() {
        assert result.statusCode == HttpStatus.CREATED
        assertThatIsUUID(result.body.id as String)
        assert result.body.errors == []
        this
    }

    ProductInCatalogAssertion wasNotAddedToCatalogForDuplicate() {
        assert result.statusCode == HttpStatus.UNPROCESSABLE_ENTITY
        assert result.body.id == null
        assert result.body.errors == ['Product with that name already exists']
        this
    }

    ProductInCatalogAssertion wasNotAddedToCatalogForWrongName() {
        assert result.statusCode == HttpStatus.UNPROCESSABLE_ENTITY
        assert result.body.id == null
        assert result.body.errors == ['Wrong product name']
        this
    }

    ProductInCatalogAssertion wasNotAddedToCatalogForWrongPrice() {
        assert result.statusCode == HttpStatus.UNPROCESSABLE_ENTITY
        assert result.body.id == null
        assert result.body.errors == ['Wrong product price']
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
