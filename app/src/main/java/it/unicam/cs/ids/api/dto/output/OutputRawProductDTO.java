package it.unicam.cs.ids.api.dto.output;

public record OutputRawProductDTO(int contentId,
                                  int supplyChainPointId,
                                  String name,
                                  String description,
                                  String author,
                                  String certification,
                                  String variety,
                                  String productionMethod) {
}
