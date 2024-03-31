package pl.tjanek.productscatalog.domain;

import java.util.List;

public record ProductAddToCatalogResult(ProductId id, Result result, List<String> errors) {

    public static ProductAddToCatalogResult success(ProductId id) {
        return new ProductAddToCatalogResult(id, Result.Success, List.of());
    }

    public static ProductAddToCatalogResult failure(List<String> errors) {
        return new ProductAddToCatalogResult(null, Result.Failure, errors);
    }

    enum Result {
        Success,
        Failure,
    }
}
