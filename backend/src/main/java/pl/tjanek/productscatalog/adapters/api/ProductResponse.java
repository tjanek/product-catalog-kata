package pl.tjanek.productscatalog.adapters.api;

import pl.tjanek.productscatalog.domain.Product;

import java.math.BigDecimal;

record ProductResponse(String id, String name, BigDecimal price) {

    static ProductResponse from(Product product) {
        return new ProductResponse(
                product.id().id(),
                product.name().name(),
                product.price().price());
    }
}
