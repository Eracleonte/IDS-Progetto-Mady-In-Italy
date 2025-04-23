package it.cs.unicam.progetto.mady.in.italy.dto.input;

public record InputRawProductDTO(int supplyChainPointId,
                                 String name,
                                 String description,
                                 String author,
                                 String certification,
                                 String variety,
                                 String productionMethod) {
}
