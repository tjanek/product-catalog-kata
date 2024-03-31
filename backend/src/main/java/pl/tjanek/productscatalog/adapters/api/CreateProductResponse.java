package pl.tjanek.productscatalog.adapters.api;

import java.util.List;

record CreateProductResponse(String id, List<String> errors) {

    static CreateProductResponse success(String id) {
        return new CreateProductResponse(id, List.of());
    }

    static CreateProductResponse failure(List<String> errors) {
        return new CreateProductResponse(null, errors);
    }

}
