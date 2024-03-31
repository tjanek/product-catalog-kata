package pl.tjanek.productscatalog.adapters.api;

import java.util.List;

record ErrorResponse(List<String> errors) {
}
