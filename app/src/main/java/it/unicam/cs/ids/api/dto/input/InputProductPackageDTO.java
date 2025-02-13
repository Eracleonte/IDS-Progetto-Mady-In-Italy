package it.unicam.cs.ids.api.dto.input;

import java.util.List;

public record InputProductPackageDTO(int supplyChainPointId,
                                     String name,
                                     String description,
                                     String author,
                                     List<InputProductPackageElementDTO> packageElements) {

}
