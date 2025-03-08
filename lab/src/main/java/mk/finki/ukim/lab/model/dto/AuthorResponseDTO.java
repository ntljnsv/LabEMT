package mk.finki.ukim.lab.model.dto;

import mk.finki.ukim.lab.model.Author;

public record AuthorResponseDTO(Long id, String name, String surname, Long countryId) {

    public static AuthorResponseDTO fromEntity(Author author) {
        return new AuthorResponseDTO(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getCountry().getId()
        );
    }
}
