package mk.finki.ukim.lab.dto;

import mk.finki.ukim.lab.model.domain.Country;

public record CountryResponseDTO (Long id, String name, String continent) {

    public static CountryResponseDTO fromEntity(Country country) {

        return new CountryResponseDTO(
                country.getId(),
                country.getName(),
                country.getContinent()
        );
    }
}
