package pl.tjanek.productscatalog.adapters.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.tjanek.productscatalog.domain.Product;
import pl.tjanek.productscatalog.domain.ProductId;
import pl.tjanek.productscatalog.domain.ProductName;
import pl.tjanek.productscatalog.domain.ProductPrice;
import pl.tjanek.productscatalog.domain.ProductsRepository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class ProductsJpaDbRepository implements ProductsRepository {

    private final ProductsJpaRepository repository;

    @Override
    public Product save(Product product) {
        ProductsJpaEntity entity = ProductsJpaEntity.from(product);
        return ProductsJpaEntity.to(repository.save(entity));
    }

    @Override
    public Collection<Product> findAll() {
        return repository.findAllByOrderByNameAsc().stream()
                .map(ProductsJpaEntity::to)
                .toList();
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return repository.findById(id.id())
                .map(ProductsJpaEntity::to);
    }

    @Override
    @Transactional
    public boolean deleteById(ProductId id) {
        var existsById = repository.existsById(id.id());
        if (!existsById) {
            return false;
        }
        repository.deleteById(id.id());
        return true;
    }

    @Override
    public boolean existsByName(ProductName name) {
        return repository.existsByNameEqualsIgnoreCase(name.name());
    }

}

@Repository
interface ProductsJpaRepository extends JpaRepository<ProductsJpaEntity, String> {
    List<ProductsJpaEntity> findAllByOrderByNameAsc();
    boolean existsByNameEqualsIgnoreCase(String name);
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
class ProductsJpaEntity {

    @Id
    private String id;
    private String name;
    private BigDecimal price;

    static ProductsJpaEntity from(Product product) {
        ProductsJpaEntity entity = new ProductsJpaEntity();
        entity.id = product.id().id();
        entity.name = product.name().name();
        entity.price = product.price().price();
        return entity;
    }

    static Product to(ProductsJpaEntity entity) {
        return new Product(
                ProductId.of(entity.getId()),
                ProductName.of(entity.getName()),
                ProductPrice.of(entity.getPrice().toString())
        );
    }

}

