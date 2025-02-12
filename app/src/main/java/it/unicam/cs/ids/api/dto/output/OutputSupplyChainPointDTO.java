package it.unicam.cs.ids.api.dto.output;

public record OutputSupplyChainPointDTO(int id,
                                        float latitude,
                                        float longitude,
                                        String name,
                                        boolean isProduction,
                                        boolean isTransformation,
                                        boolean isDistribution,
                                        boolean isResale) {

}
