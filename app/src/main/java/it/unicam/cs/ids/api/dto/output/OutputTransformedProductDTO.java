package it.unicam.cs.ids.api.dto.output;

public record OutputTransformedProductDTO(int contentId,
                                          int supplyChainPointId,
                                          String name,
                                          String description,
                                          String author,
                                          String certification,
                                          String variety,
                                          int transformationProcessId){
}
