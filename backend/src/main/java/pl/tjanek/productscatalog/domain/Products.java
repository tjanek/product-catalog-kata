package pl.tjanek.productscatalog.domain;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class Products {

    private final ProductsRepository productsRepository;
    private final ProductsIdProvider productsIdProvider;

    public Try<ProductAddToCatalogResult> addProduct(ProductName name, ProductPrice initialPrice) {
        return Try.of(() -> {
            var validateProduct = ProductValidator
                    .validateProduct(name, initialPrice, productsIdProvider::getProductId);
            if (validateProduct.isValid()) {
                boolean existsByName = productsRepository.existsByName(name);
                if (existsByName) {
                    return ProductAddToCatalogResult.failure(List.of("Product with that name already exists"));
                }
                var product = validateProduct.get();
                productsRepository.save(product);
                return ProductAddToCatalogResult.success(product.id());
            } else {
                var errors = validateProduct.getError().toJavaList();
                return ProductAddToCatalogResult.failure(errors);
            }
        });
    }

    public Try<Collection<Product>> getProducts() {
        return Try.of(productsRepository::findAll);
    }

    public Try<Optional<Product>> getProduct(ProductId id) {
        return Try.of(() -> productsRepository.findById(id));
    }

    public Try<Boolean> deleteProduct(ProductId id) {
        return Try.of(() -> productsRepository.deleteById(id));
    }

}
