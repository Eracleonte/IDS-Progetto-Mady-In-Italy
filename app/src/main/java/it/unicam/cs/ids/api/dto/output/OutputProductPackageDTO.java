package it.unicam.cs.ids.api.dto.output;

import it.unicam.cs.ids.api.dto.input.InputProductPackageElementDTO;

import java.util.List;

public record OutputProductPackageDTO(int id,
                                      int supplyChainPointId,
                                      String name,
                                      String description,
                                      String author,
                                      List<OutputProductPackageElementDTO> packageElements) {
}
