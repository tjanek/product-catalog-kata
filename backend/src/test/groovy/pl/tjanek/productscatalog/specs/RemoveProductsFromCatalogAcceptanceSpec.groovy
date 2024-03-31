package pl.tjanek.productscatalog.specs

import pl.tjanek.productscatalog.BaseIntegrationSpec
import pl.tjanek.productscatalog.abilities.products.AddProductAbility
import pl.tjanek.productscatalog.abilities.products.DeleteProductByIdAbility
import pl.tjanek.productscatalog.abilities.products.GetProductByIdAbility

import static pl.tjanek.productscatalog.assertions.products.ProductInCatalogAssertion.assertThatProductInCatalog
import static pl.tjanek.productscatalog.assertions.products.RemoveProductsFromCatalogAssertion.assertThatRemovedProductsFromCatalog

class RemoveProductsFromCatalogAcceptanceSpec extends BaseIntegrationSpec
            implements AddProductAbility, DeleteProductByIdAbility, GetProductByIdAbility {

    // @formatter:off

    def "should remove existing product from catalog"() {
        given: "add new product to catalog"
            def productToRemoveId = addProductToCatalog("Product to remove", "79.99").body.id as String
            def productToNotRemoveId = addProductToCatalog("Product to not remove", "99.99").body.id as String
        when: "remove product from catalog"
            def productRemoved = deleteProductById(productToRemoveId)
        then: "removed product should not be in catalog"
            assertThatRemovedProductsFromCatalog(productRemoved)
                .wasRemoved()
        and:
            def productToRemove = getProduct(productToRemoveId)
            assertThatProductInCatalog(productToRemove)
                .isNotInCatalog()
        and: "other not removed product should be in catalog"
            def productToNotRemove = getProduct(productToNotRemoveId)
            assertThatProductInCatalog(productToNotRemove)
                .isInCatalog()
    }

    def "should not remove non existing products from catalog"() {
        when: "remove non existing product from catalog"
            def productNotRemoved = deleteProductById('fake-product-id')
        then: "products should not be removed from catalog"
            assertThatRemovedProductsFromCatalog(productNotRemoved)
                .wasNotFound()
    }

    // @formatter:on

}
