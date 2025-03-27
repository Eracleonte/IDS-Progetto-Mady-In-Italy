package it.unicam.cs.ids.api.dto.output;

import it.unicam.cs.ids.api.abstractions.Identifiable;
import it.unicam.cs.ids.api.dto.input.InputProductPackageElementDTO;

import java.util.List;

public record OutputProductPackageDTO(int id,
                                      int supplyChainPointId,
                                      String name,
                                      String description,
                                      String author,
                                      List<OutputProductPackageElementDTO> packageElements) implements Identifiable {
    @Override
    public int getId() {
        return this.id;
    }

}
