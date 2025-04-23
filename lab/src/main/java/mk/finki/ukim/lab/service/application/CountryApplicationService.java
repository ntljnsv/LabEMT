package mk.finki.ukim.lab.service.application;

import mk.finki.ukim.lab.dto.CountryRequestDTO;
import mk.finki.ukim.lab.dto.CountryResponseDTO;
import mk.finki.ukim.lab.dto.NumAuthorsByCountryResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {

    List<CountryResponseDTO> listAll();

    Optional<CountryResponseDTO> findById(Long id);

    Optional<CountryResponseDTO> create(CountryRequestDTO countryRequestDTO);

    Optional<CountryResponseDTO> update(Long id, CountryRequestDTO countryRequestDTO);

    boolean deleteById(Long id);

    Optional<NumAuthorsByCountryResponseDTO> numAuthorsByCountry(Long id);
}
