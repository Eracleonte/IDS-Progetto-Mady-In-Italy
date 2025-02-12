package it.unicam.cs.ids.api.dto.input;

public record InputTransformedProductDTO(int supplyChainPointId,
                                         String name,
                                         String description,
                                         String author,
                                         String certification,
                                         String variety,
                                         int transformationProcessId) {
}
