package mk.finki.ukim.lab.dto;

import mk.finki.ukim.lab.model.enums.Role;

public record UserCreateDTO(String username,
                            String password, String repeatPassword,
                            String name, String surname,
                            Role role) {
}
