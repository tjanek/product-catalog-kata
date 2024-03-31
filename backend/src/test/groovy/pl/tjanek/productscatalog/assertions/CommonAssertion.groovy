package pl.tjanek.productscatalog.assertions

import java.util.regex.Pattern

class CommonAssertion {

    static final Pattern UUID_PATTERN =
            ~/^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/

    def assertThatIsUUID(String value) {
        assert value ==~ UUID_PATTERN
    }

}
