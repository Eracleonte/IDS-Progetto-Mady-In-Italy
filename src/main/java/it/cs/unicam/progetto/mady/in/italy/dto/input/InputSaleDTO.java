package it.cs.unicam.progetto.mady.in.italy.dto.input;

import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;

public record InputSaleDTO(int supplyChainPointId,
                           int productId,
                           ContentType productType,
                           String name,
                           String description,
                           String author,
                           float price,
                           int quantity) {

    public InputSaleDTO {
        if (!productType.equals(ContentType.RAW_PRODUCT) && !productType.equals(ContentType.TRANSFORMED_PRODUCT)
                && !productType.equals(ContentType.PRODUCT_PACKAGE))
            throw new IllegalArgumentException("Invalid product type for a sale");
    }

    public static InputSaleDTO getRawProductInputSaleDto(int supplyChainPointId,
                                                         int productId,
                                                         String name,
                                                         String description,
                                                         String author,
                                                         float price,
                                                         int quantity) {
        return new InputSaleDTO(supplyChainPointId, productId, ContentType.RAW_PRODUCT, name, description, author, price, quantity);
    }

    public static InputSaleDTO getTransformedInputSaleDto(int supplyChainPointId,
                                                          int productId,
                                                          String name,
                                                          String description,
                                                          String author,
                                                          float price,
                                                          int quantity) {
        return new InputSaleDTO(supplyChainPointId, productId, ContentType.TRANSFORMED_PRODUCT, name, description, author, price, quantity);
    }

    public static InputSaleDTO getProductPackageInputSaleDto(int supplyChainPointId,
                                                             int productId,
                                                             String name,
                                                             String description,
                                                             String author,
                                                             float price,
                                                             int quantity) {
        return new InputSaleDTO(supplyChainPointId, productId, ContentType.PRODUCT_PACKAGE, name, description, author, price, quantity);
    }

}
