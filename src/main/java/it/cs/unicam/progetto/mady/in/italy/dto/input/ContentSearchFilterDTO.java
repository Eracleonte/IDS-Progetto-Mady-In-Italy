package it.cs.unicam.progetto.mady.in.italy.dto.input;

import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;

/**
 * A simple filter used to find content.
 */
public record ContentSearchFilterDTO(Integer supplyChainPointId,
                                     String contentName,
                                     ContentType contentType,
                                     String authorName) {

    public static ContentSearchFilterDTO getRawProductsContentSearchFilterDTO(int supplyChainPointId,
                                                                              String contentName,
                                                                              String authorName) {
        return new ContentSearchFilterDTO(supplyChainPointId,contentName,ContentType.RAW_PRODUCT,authorName);
    }

    public static ContentSearchFilterDTO getTransformedProductContentSearchFilterDTO(int supplyChainPointId,
                                                                                     String contentName,
                                                                                     String authorName) {
        return new ContentSearchFilterDTO(supplyChainPointId,contentName,ContentType.TRANSFORMED_PRODUCT,authorName);
    }

    public static ContentSearchFilterDTO getProductPackageContentSearchFilterDTO(int supplyChainPointId,
                                                                                 String contentName,
                                                                                 String authorName) {
        return new ContentSearchFilterDTO(supplyChainPointId,contentName,ContentType.PRODUCT_PACKAGE,authorName);
    }

    public static ContentSearchFilterDTO getSaleContentSearchFilterDTO(int supplyChainPointId,
                                                                                 String contentName,
                                                                                 String authorName) {
        return new ContentSearchFilterDTO(supplyChainPointId,contentName,ContentType.SALE,authorName);
    }

    public static ContentSearchFilterDTO getTransformationProcessesContentSearchFilterDTO(int supplyChainPointId,
                                                                       String contentName,
                                                                       String authorName) {
        return new ContentSearchFilterDTO(supplyChainPointId,contentName,ContentType.TRANSFORMATION_PROCESS,authorName);
    }

}
