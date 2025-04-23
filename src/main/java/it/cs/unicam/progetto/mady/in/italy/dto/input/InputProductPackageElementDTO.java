package it.cs.unicam.progetto.mady.in.italy.dto.input;

import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;

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
