package pl.tjanek.productscatalog.specs


import pl.tjanek.productscatalog.BaseIntegrationSpec
import pl.tjanek.productscatalog.abilities.products.AddProductAbility
import pl.tjanek.productscatalog.abilities.products.GetProductByIdAbility

import static pl.tjanek.productscatalog.assertions.products.ProductInCatalogAssertion.assertThatProductInCatalog

class AddProductsToCatalogAcceptanceSpec extends BaseIntegrationSpec
            implements AddProductAbility, GetProductByIdAbility {

    // @formatter:off

    def "should add new product to catalog"() {
        given: "new product"
            def productName = "Designing Data-Intensive Applications"
            def initialPrice = "159.99"
        when: "try to add new product"
            def product = addProductToCatalog(productName, initialPrice)
        then: "product should be added to catalog"
            assertThatProductInCatalog(product)
                .wasAddedToCatalog()
        and: "product should be in catalog"
            def productId = product.body.id as String
            def productFromCatalog = getProduct(productId)
            assertThatProductInCatalog(productFromCatalog)
                .isInCatalog()
                .hasName(productName)
                .hasPrice(initialPrice)
    }

    def "should add new product twice to catalog"() {
        given: "new product"
            def productName = "Clean Code"
            def initialPrice = "199.99"
        when: "try to add new product"
            def product = addProductToCatalog(productName, initialPrice)
            assertThatProductInCatalog(product)
                .wasAddedToCatalog()
        then: "try to add same product one more time"
            def duplicateProduct = addProductToCatalog(productName.toLowerCase(), initialPrice)
            assertThatProductInCatalog(duplicateProduct)
                .wasNotAddedToCatalogForDuplicate()
    }

    def "should not add new product with empty price to catalog"() {
        given: "new product"
            def productName = "Extreme Programming"
            def initialPrice = ""
        when: "try to add new product"
            def product = addProductToCatalog(productName, initialPrice)
        then: "product should not be added to catalog"
            assertThatProductInCatalog(product)
                .wasNotAddedToCatalogForWrongPrice()
    }

    def "should not add new product with zero price to catalog"() {
        given: "new product"
            def productName = "Extreme Programming"
            def initialPrice = "0.00"
        when: "try to add new product"
            def product = addProductToCatalog(productName, initialPrice)
        then: "product should not be added to catalog"
            assertThatProductInCatalog(product)
                .wasNotAddedToCatalogForWrongPrice()
    }

    def "should not add new product with negative price to catalog"() {
        given: "new product"
            def productName = "Extreme Programming"
            def initialPrice = "-15.00"
        when: "try to add new product"
            def product = addProductToCatalog(productName, initialPrice)
        then: "product should not be added to catalog"
            assertThatProductInCatalog(product)
                .wasNotAddedToCatalogForWrongPrice()
    }

    def "should not add new product with empty name to catalog"() {
        given: "new product"
            def productName = " "
            def initialPrice = "59.99"
        when: "try to add new product"
            def product = addProductToCatalog(productName, initialPrice)
        then: "product should not be added to catalog"
            assertThatProductInCatalog(product)
                .wasNotAddedToCatalogForWrongName()
    }

    def "should not add new product with wrong name to catalog"() {
        given: "new product"
            def productName = "@%!#"
            def initialPrice = "29.99"
        when: "try to add new product"
            def product = addProductToCatalog(productName, initialPrice)
        then: "product should not be added to catalog"
            assertThatProductInCatalog(product)
                .wasNotAddedToCatalogForWrongName()
    }

    // @formatter:on

}
