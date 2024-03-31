package pl.tjanek.productscatalog.domain;

public record ProductId(String id) {

    public static ProductId of(String id) {
        return new ProductId(id);
    }

}
