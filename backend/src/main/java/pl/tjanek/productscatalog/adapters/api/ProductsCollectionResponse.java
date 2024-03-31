package pl.tjanek.productscatalog.adapters.api;

import java.util.Collection;

record ProductsCollectionResponse(Collection<ProductResponse> products) {
}
