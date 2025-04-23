package it.cs.unicam.progetto.mady.in.italy.dto.output;

import it.cs.unicam.progetto.mady.in.italy.abstractions.Identifiable;

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
