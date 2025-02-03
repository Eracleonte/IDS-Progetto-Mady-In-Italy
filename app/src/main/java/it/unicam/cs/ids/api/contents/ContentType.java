package it.unicam.cs.ids.api.contents;

public enum ContentType {

    PRODUCT_ON_SALE("PRODUCT_ON_SALE"),

    RAW_PRODUCT("RAW_PRODUCT"),

    TRANSFORMED_PRODUCT("TRANSFORMED_PRODUCT"),

    PRODUCT_PACKAGE("PRODUCT_PACKAGE"),

    TRANSFORMATION_PROCESS("TRANSFORMATION_PROCESS");

    private final String value;

    ContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
