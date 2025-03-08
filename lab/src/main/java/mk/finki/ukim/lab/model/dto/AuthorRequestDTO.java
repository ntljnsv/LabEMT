package mk.finki.ukim.lab.model.dto;

import mk.finki.ukim.lab.model.Author;
import mk.finki.ukim.lab.model.Country;

public record AuthorRequestDTO(String name, String surname, Long countryId) {

    public Author toEntity(Country country) {
        return new Author(name, surname, country);
    }
}
