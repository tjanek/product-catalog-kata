package pl.tjanek.productscatalog.adapters.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.tjanek.productscatalog.domain.ProductId;
import pl.tjanek.productscatalog.domain.ProductName;
import pl.tjanek.productscatalog.domain.ProductPrice;
import pl.tjanek.productscatalog.domain.Products;

import java.util.List;

@RequiredArgsConstructor
@Component
class ProductApi {

    private final Products products;

    ResponseEntity<?> addProduct(CreateProductRequest request) {
        var response = products.addProduct(ProductName.of(request.name()), ProductPrice.of(request.initialPrice()));
        if (response.isSuccess()) {
            var result = response.get();
            return result.errors() == null || result.errors().isEmpty() ?
                    ResponseEntity.status(HttpStatus.CREATED)
                            .body(CreateProductResponse.success(result.id().id())) :
                    ResponseEntity.unprocessableEntity()
                            .body(CreateProductResponse.failure(result.errors()));
        }

        return defaultErrorResponse();
    }

    ResponseEntity<?> findAllProducts() {
        var response = products.getProducts();
        if (response.isSuccess()) {
            return ResponseEntity.ok(new ProductsCollectionResponse(
                    response.get().stream()
                    .map(ProductResponse::from)
                    .toList()));
        }

        return defaultErrorResponse();
    }

    ResponseEntity<?> findProductById(String id) {
        var response = products.getProduct(ProductId.of(id));
        if (response.isSuccess()) {
                return response.get()
                .map(ProductResponse::from)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        return defaultErrorResponse();
    }

    ResponseEntity<?> deleteProductById(String id) {
        var response = products.deleteProduct(ProductId.of(id));
        if (response.isSuccess()) {
            var deleted = response.get();
            return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        }

        return defaultErrorResponse();
    }

    private static ResponseEntity<ErrorResponse> defaultErrorResponse() {
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(List.of("Something went wrong")));
    }

}
