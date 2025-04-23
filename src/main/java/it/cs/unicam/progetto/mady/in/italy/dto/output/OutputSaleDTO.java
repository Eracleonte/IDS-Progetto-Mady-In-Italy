package it.cs.unicam.progetto.mady.in.italy.dto.output;

import it.cs.unicam.progetto.mady.in.italy.abstractions.Identifiable;

/**
 * @param productId NOTE: Subject to change when switching to SpringBoot
 * @param productType NOTE: Subject to change when switching to SpringBoot
 * <p>
 * When switching to Springboot we will be able to make OutputSaleDTO
 * that will contain the product information directly
 */
public record OutputSaleDTO(int id,
                            int supplyChainPointId,
                            int productId,
                            String productType,
                            String name,
                            String description,
                            String author,
                            float price,
                            int quantity) implements Identifiable {

    @Override
    public int getId() {
        return this.id;
    }

}
