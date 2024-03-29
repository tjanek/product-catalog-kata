package products

import abilities.HttpRequestAbility

trait ProductsHttpRequestAbility extends HttpRequestAbility {

    static String PRODUCTS_CATALOG_V1_BASE_URL = '/api/v1/products'

}