package it.unicam.cs.ids.api.dto.output;

import it.unicam.cs.ids.api.abstractions.Identifiable;

// TODO delete

public record OutputProductPackageElementDTO(int id,
                                             String productType) implements Identifiable {

    @Override
    public int getId() {
        return this.id;
    }

}
