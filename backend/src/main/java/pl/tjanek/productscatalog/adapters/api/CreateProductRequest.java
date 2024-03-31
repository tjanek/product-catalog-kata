package pl.tjanek.productscatalog.adapters.api;

import java.math.BigDecimal;

record CreateProductRequest(String name, BigDecimal initialPrice) {
}
