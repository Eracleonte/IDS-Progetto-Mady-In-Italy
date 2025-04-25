package it.unicam.cs.ids.api.dto.output;

import it.unicam.cs.ids.api.abstractions.Identifiable;

/**
 * @param transformationProcessId NOTE: Subject to change when switching to SpringBoot
 * <p>
 * When switching to Springboot we will be able to make OutputTransformedProductDTO
 * that will contain the transformation process information directly
 */
public record OutputTransformedProductDTO(int id,
                                          int supplyChainPointId,
                                          String name,
                                          String description,
                                          String author,
                                          String certification,
                                          String variety,
                                          int transformationProcessId) implements Identifiable {

    @Override
    public int getId() {
        return this.id;
    }

}
