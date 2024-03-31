package pl.tjanek.productscatalog.domain;

import java.util.Collection;
import java.util.Optional;

public interface ProductsRepository {

    Product save(Product product);
    Collection<Product> findAll();
    Optional<Product> findById(ProductId id);
    boolean deleteById(ProductId id);
    boolean existsByName(ProductName name);
}
