package it.cs.unicam.progetto.mady.in.italy.dto.output;

import it.cs.unicam.progetto.mady.in.italy.abstractions.Identifiable;

import java.util.List;

public record OutputProductPackageDTO(int id,
                                      int supplyChainPointId,
                                      String name,
                                      String description,
                                      String author,
                                      List<OutputRawProductDTO> rawProductDTOS,
                                      List<OutputTransformedProductDTO> transformedProductDTOS) implements Identifiable {

    @Override
    public int getId() {
        return this.id;
    }

}