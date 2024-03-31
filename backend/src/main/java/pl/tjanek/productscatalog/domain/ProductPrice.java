package pl.tjanek.productscatalog.domain;

import java.math.BigDecimal;

public record ProductPrice(BigDecimal price) {

    public static ProductPrice of(String price) {
        return new ProductPrice(new BigDecimal(price));
    }

    public static ProductPrice of(BigDecimal price) {
        return new ProductPrice(price);
    }

}
