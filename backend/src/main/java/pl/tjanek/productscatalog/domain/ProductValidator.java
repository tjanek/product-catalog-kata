package pl.tjanek.productscatalog.domain;

import io.micrometer.common.util.StringUtils;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

import java.math.BigDecimal;
import java.util.function.Supplier;

class ProductValidator {

    private static final String INVALID_NAME = "Wrong product name";
    private static final String INVALID_PRICE = "Wrong product price";

    public static Validation<Seq<String>, Product> validateProduct(ProductName name,
                                                            ProductPrice price,
                                                            Supplier<ProductId> productIdSupplier) {
        return Validation.combine(
                validateName(name),
                validatePrice(price)
        ).ap((productName, productPrice) -> {
            ProductId productId = productIdSupplier.get();
            return new Product(productId, name, productPrice);
        });
    }

    private static Validation<String, ProductName> validateName(ProductName name) {
        return isProductNameIsBlank(name) || isProductNameNotMatchesValidChars(name) ?
                Validation.invalid(INVALID_NAME) :
                Validation.valid(name);
    }

    private static Validation<String, ProductPrice> validatePrice(ProductPrice price) {
        return isProductPriceIsEmpty(price) || isProductPriceIsLessThanZero(price) ?
                Validation.invalid(INVALID_PRICE) :
                Validation.valid(price);
    }

    private static boolean isProductNameNotMatchesValidChars(ProductName name) {
        return !name.name().replaceAll("[a-zA-Z0-9 -.]", "").isEmpty();
    }

    private static boolean isProductNameIsBlank(ProductName name) {
        return StringUtils.isBlank(name.name());
    }

    private static boolean isProductPriceIsEmpty(ProductPrice price) {
        return price.price() == null;
    }

    private static boolean isProductPriceIsLessThanZero(ProductPrice price) {
        return price.price().compareTo(BigDecimal.ZERO) <= 0;
    }

}
