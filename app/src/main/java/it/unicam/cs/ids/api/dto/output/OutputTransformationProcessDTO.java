package it.unicam.cs.ids.api.dto.output;

import it.unicam.cs.ids.api.abstractions.Identifiable;

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
