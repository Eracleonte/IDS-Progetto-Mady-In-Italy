package it.unicam.cs.ids.api.dto.input;

public record InputProductOnSaleDTO(int supplyChainPointId,
                                    int productId,
                                    String productType,
                                    String name,
                                    String description,
                                    String author,
                                    float price,
                                    int quantity) {
}
