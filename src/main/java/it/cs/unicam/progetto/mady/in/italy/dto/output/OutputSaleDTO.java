package it.cs.unicam.progetto.mady.in.italy.dto.output;

import it.cs.unicam.progetto.mady.in.italy.abstractions.Identifiable;

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
