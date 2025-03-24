package it.unicam.cs.ids.api.dto.input;

import java.util.List;

public record InputUserDTO(String username,
                           String password,
                           String email) {
}
