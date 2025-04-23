package it.cs.unicam.progetto.mady.in.italy.dto.output;

import it.cs.unicam.progetto.mady.in.italy.abstractions.Identifiable;

public record OutputTransformationProcessDTO(int id,
                                             int supplyChainPointId,
                                             String name,
                                             String description,
                                             String author,
                                             String certification,
                                             String transformationPhases) implements Identifiable {

    @Override
    public int getId() {
        return this.id;
    }

}
