package mk.finki.ukim.lab.dto;

import mk.finki.ukim.lab.model.domain.User;
import mk.finki.ukim.lab.model.enums.Role;

public record UserResponseDTO(String username, String name, String surname, Role role) {

    public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }

    public User toEntity() {
        return new User(username, name, surname, role.name());
    }
}
