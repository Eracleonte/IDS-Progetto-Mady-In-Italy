package it.cs.unicam.progetto.mady.in.italy.dto.input;

import java.util.List;

public record InputProductPackageDTO(int supplyChainPointId,
                                     String name,
                                     String description,
                                     String author,
                                     List<InputProductPackageElementDTO> packageElements) {

}
