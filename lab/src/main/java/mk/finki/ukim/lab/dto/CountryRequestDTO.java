package mk.finki.ukim.lab.dto;

import mk.finki.ukim.lab.model.domain.Country;

public record CountryRequestDTO (String name, String continent) {

    public Country toEntity() {
        return new Country(name, continent);
    }
}
