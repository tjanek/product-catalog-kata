package pl.tjanek.productscatalog.domain;

public record ProductName(String name) {

    public static ProductName of(String name) {
        return new ProductName(name);
    }

}
