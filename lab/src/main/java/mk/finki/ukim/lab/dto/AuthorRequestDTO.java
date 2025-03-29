package mk.finki.ukim.lab.dto;

import mk.finki.ukim.lab.model.domain.Author;
import mk.finki.ukim.lab.model.domain.Country;

public record AuthorRequestDTO(String name, String surname, Long countryId) {

    public Author toEntity(Country country) {
        return new Author(name, surname, country);
    }
}
