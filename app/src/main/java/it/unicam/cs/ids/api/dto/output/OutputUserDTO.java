package it.unicam.cs.ids.api.dto.output;

import it.unicam.cs.ids.api.abstractions.Identifiable;

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
