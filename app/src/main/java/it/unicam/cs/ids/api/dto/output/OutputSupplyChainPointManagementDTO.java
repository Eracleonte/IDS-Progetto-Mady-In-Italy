package it.unicam.cs.ids.api.dto.output;

import it.unicam.cs.ids.api.abstractions.Identifiable;

public record OutputSupplyChainPointManagementDTO(int id,
                                                  int supplyChainPointId,
                                                  int userId) implements Identifiable {

    @Override
    public int getId() {
        return this.id;
    }

}
