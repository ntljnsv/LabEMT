package mk.finki.ukim.lab.model.dto;

import mk.finki.ukim.lab.model.Country;

public record CountryRequestDTO (String name, String continent) {

    public Country toEntity() {
        return new Country(name, continent);
    }
}
