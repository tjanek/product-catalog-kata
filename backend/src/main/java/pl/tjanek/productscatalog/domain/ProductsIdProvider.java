package pl.tjanek.productscatalog.domain;

import org.springframework.stereotype.Component;

import java.util.UUID;

interface ProductsIdProvider {

    ProductId getProductId();

}

@Component
class DefaultProductsIdProvider implements ProductsIdProvider {

    public ProductId getProductId() {
        return new ProductId(UUID.randomUUID().toString());
    }

}
