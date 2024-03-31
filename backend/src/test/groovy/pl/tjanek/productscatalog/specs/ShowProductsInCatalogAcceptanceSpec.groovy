package pl.tjanek.productscatalog.specs

import pl.tjanek.productscatalog.BaseIntegrationSpec
import pl.tjanek.productscatalog.abilities.products.AddProductAbility
import pl.tjanek.productscatalog.abilities.products.GetProductByIdAbility
import pl.tjanek.productscatalog.abilities.products.GetProductsAbility

import static pl.tjanek.productscatalog.assertions.products.ProductInCatalogAssertion.assertThatProductInCatalog
import static pl.tjanek.productscatalog.assertions.products.ProductsInCatalogAssertion.assertThatProductsInCatalog

class ShowProductsInCatalogAcceptanceSpec extends BaseIntegrationSpec
            implements AddProductAbility, GetProductsAbility, GetProductByIdAbility {

    // @formatter:off

    def "should show some products in catalog"() {
        given: "add new products to catalog"
            def vol1Id = addProductToCatalog("Harry Potter vol. 1", "19.99").body.id as String
            def vol2Id = addProductToCatalog("Harry Potter vol. 2", "29.99").body.id as String
            def vol3Id = addProductToCatalog("Harry Potter vol. 3", "39.99").body.id as String
        when: "show product catalog"
            def products = getProducts()
        then: "products should be in catalog"
                assertThatProductsInCatalog(products)
                    .hasProduct(vol1Id, "Harry Potter vol. 1", "19.99")
                    .hasProduct(vol2Id, "Harry Potter vol. 2", "29.99")
                    .hasProduct(vol3Id, "Harry Potter vol. 3", "39.99")
    }

    def "should not show non existing products in catalog"() {
        when: "show non existing product in catalog"
            def product = getProduct('fake-product-id')
        then: "products should not be found in catalog"
            assertThatProductInCatalog(product)
                .isNotInCatalog()
    }

    // @formatter:on

}
