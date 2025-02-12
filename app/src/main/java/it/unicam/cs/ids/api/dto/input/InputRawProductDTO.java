package it.unicam.cs.ids.api.dto.input;

public record InputRawProductDTO(int supplyChainPointId,
                                 String name,
                                 String description,
                                 String author,
                                 String certification,
                                 String variety,
                                 String productionMethod) {
}
