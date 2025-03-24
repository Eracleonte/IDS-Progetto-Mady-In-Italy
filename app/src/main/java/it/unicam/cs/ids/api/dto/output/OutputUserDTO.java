package it.unicam.cs.ids.api.dto.output;

import java.util.List;

public record OutputUserDTO(int id,
                            String name,
                            String email,
                            List<String> roles) {
}
