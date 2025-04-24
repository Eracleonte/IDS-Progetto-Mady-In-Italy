package it.cs.unicam.progetto.mady.in.italy.model.contents;

/**
 * Represents the ContentType of a Content
 */
public enum ContentType {

    SALE("SALE"),

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
