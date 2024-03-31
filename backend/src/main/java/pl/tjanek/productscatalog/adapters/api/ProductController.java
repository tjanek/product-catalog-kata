package pl.tjanek.productscatalog.adapters.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
class ProductController {

    private final ProductApi api;

    @PostMapping
    ResponseEntity<?> addProduct(@RequestBody CreateProductRequest request) {
        return api.addProduct(request);
    }

    @GetMapping
    ResponseEntity<?> findAllProducts() {
        return api.findAllProducts();
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<?> findProductById(@PathVariable String id) {
        return api.findProductById(id);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteProductById(@PathVariable String id) {
        return api.deleteProductById(id);
    }

}
