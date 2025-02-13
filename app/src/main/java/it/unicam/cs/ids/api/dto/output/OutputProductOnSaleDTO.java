package it.unicam.cs.ids.api.dto.output;

public record OutputProductOnSaleDTO(int contentId,
                                     int supplyChainPointId,
                                     int productId,
                                     String productType,
                                     String name,
                                     String description,
                                     String author,
                                     float price,
                                     int quantity) {
}
