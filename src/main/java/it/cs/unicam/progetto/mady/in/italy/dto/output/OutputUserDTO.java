package it.cs.unicam.progetto.mady.in.italy.dto.output;

import it.cs.unicam.progetto.mady.in.italy.abstractions.Identifiable;

import java.util.List;

public record OutputUserDTO(int id,
                            String name,
                            String email,
                            List<String> roles) implements Identifiable {

    @Override
    public int getId() {
        return this.id;
    }

}
