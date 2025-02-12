package it.unicam.cs.ids.api.dto.output;

public record OutputValidationRequestDTO(int id,
                                         int supplyChainPointId,
                                         int contentId,
                                         String contentType) {

}
