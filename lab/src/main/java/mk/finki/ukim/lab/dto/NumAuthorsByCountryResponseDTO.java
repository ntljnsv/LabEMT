package mk.finki.ukim.lab.dto;

import mk.finki.ukim.lab.model.views.NumAuthorsByCountry;

public record NumAuthorsByCountryResponseDTO(Long countryId, Integer numAuthors) {

    public static NumAuthorsByCountryResponseDTO fromEntity(NumAuthorsByCountry numAuthorsByCountry) {

        return new NumAuthorsByCountryResponseDTO(
                numAuthorsByCountry.getCountryId(),
                numAuthorsByCountry.getNumAuthors()
        );
    }
}
