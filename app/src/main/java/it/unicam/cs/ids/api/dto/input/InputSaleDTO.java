package it.unicam.cs.ids.api.dto.input;

public record InputSaleDTO(int supplyChainPointId,
                           int productId,
                           String productType,
                           String name,
                           String description,
                           String author,
                           float price,
                           int quantity) {
}
