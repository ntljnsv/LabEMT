package mk.finki.ukim.lab.dto;

import mk.finki.ukim.lab.model.projections.AuthorProjection;

public record AuthorProjectionDTO(String name, String surname) {

    public static AuthorProjectionDTO fromEntity(AuthorProjection authorProjection) {

        return new AuthorProjectionDTO(authorProjection.getName(), authorProjection.getSurname());
    }
}
