package it.cs.unicam.progetto.mady.in.italy.dto.output;

import it.cs.unicam.progetto.mady.in.italy.abstractions.Identifiable;

public record OutputSupplyChainPointManagementDTO(int id,
                                                  int supplyChainPointId,
                                                  int userId) implements Identifiable {

    @Override
    public int getId() {
        return this.id;
    }

}
