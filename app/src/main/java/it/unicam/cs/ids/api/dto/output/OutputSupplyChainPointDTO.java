package it.unicam.cs.ids.api.dto.output;

import it.unicam.cs.ids.api.abstractions.Identifiable;

public record OutputSupplyChainPointDTO(int id,
                                        float latitude,
                                        float longitude,
                                        String name,
                                        boolean isProduction,
                                        boolean isTransformation,
                                        boolean isDistribution,
                                        boolean isResale) implements Identifiable {

    @Override
    public int getId() {
        return this.id;
    }

}
