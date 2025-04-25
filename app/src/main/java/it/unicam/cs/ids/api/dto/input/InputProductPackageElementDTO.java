package it.unicam.cs.ids.api.dto.input;

import it.unicam.cs.ids.api.model.contents.ContentType;

public record InputProductPackageElementDTO(int productId,
                                            ContentType productType) {

    public InputProductPackageElementDTO {
        if (!ContentType.RAW_PRODUCT.equals(productType) && !ContentType.TRANSFORMED_PRODUCT.equals(productType))
            throw new IllegalArgumentException("Invalid productType: " + productType.getValue());
    }

    public static InputProductPackageElementDTO getRawProductPackageElementDTO(int id) {
        return new InputProductPackageElementDTO(id, ContentType.RAW_PRODUCT);
    }

    public static InputProductPackageElementDTO getTransformedProductPackageElementDTO(int id) {
        return new InputProductPackageElementDTO(id, ContentType.TRANSFORMED_PRODUCT);
    }

}
