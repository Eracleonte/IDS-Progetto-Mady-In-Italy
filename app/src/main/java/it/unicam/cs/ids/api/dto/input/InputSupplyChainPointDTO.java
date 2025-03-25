package it.unicam.cs.ids.api.dto.input;

public record InputSupplyChainPointDTO(float latitude,
                                       float longitude,
                                       String name,
                                       boolean isProduction,
                                       boolean isTransformation,
                                       boolean isDistribution,
                                       boolean isResale) {
}
