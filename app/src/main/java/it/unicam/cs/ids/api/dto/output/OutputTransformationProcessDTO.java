package it.unicam.cs.ids.api.dto.output;

public record OutputTransformationProcessDTO(int contentId,
                                             int supplyChainPointId,
                                             String name,
                                             String description,
                                             String author,
                                             String certification,
                                             String transformationPhases) {
}
