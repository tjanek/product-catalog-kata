package pl.tjanek.productscatalog.abilities.products


import pl.tjanek.productscatalog.abilities.HttpRequestAbility

trait ProductsHttpRequestAbility extends HttpRequestAbility {

    static String PRODUCTS_CATALOG_V1_BASE_URL = '/api/v1/products'

}