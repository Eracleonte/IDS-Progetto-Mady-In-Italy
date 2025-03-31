package it.unicam.cs.ids.api.dto.output;

import it.unicam.cs.ids.api.abstractions.Identifiable;

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