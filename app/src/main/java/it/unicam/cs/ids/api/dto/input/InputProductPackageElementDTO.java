package it.unicam.cs.ids.api.dto.input;

import it.unicam.cs.ids.api.model.contents.ContentType;

public record InputProductPackageElementDTO(int productId,
                                            String productType) {

    public InputProductPackageElementDTO {
        if (!"RAW_PRODUCT".equals(productType) && !"TRANSFORMED_PRODUCT".equals(productType))
            throw new IllegalArgumentException("Invalid productType: " + productType);
    }

    public static InputProductPackageElementDTO getRawProductPackageElementDTO(int id) {
        return new InputProductPackageElementDTO(id, ContentType.RAW_PRODUCT.getValue());
    }

    public static InputProductPackageElementDTO getTransformedProductPackageElementDTO(int id) {
        return new InputProductPackageElementDTO(id, ContentType.TRANSFORMED_PRODUCT.getValue());
    }

}
