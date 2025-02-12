package it.unicam.cs.ids.api.dto.input;

public record InputTransformationProcessDTO(int supplyChainPointId,
         String name,
         String description,
         String author,
         String certification,
         String transformationPhases) {
}
