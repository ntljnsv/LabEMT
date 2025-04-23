package mk.finki.ukim.lab.service.application.impl;

import mk.finki.ukim.lab.dto.CountryRequestDTO;
import mk.finki.ukim.lab.dto.CountryResponseDTO;
import mk.finki.ukim.lab.dto.NumAuthorsByCountryResponseDTO;
import mk.finki.ukim.lab.service.application.CountryApplicationService;
import mk.finki.ukim.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<CountryResponseDTO> listAll() {
        return countryService.listAll().stream().map(CountryResponseDTO::fromEntity).toList();
    }

    @Override
    public Optional<CountryResponseDTO> findById(Long id) {
        return countryService.findById(id).map(CountryResponseDTO::fromEntity);
    }

    @Override
    public Optional<CountryResponseDTO> create(CountryRequestDTO countryRequestDTO) {

        return countryService
                .create(countryRequestDTO.name(), countryRequestDTO.continent())
                .map(CountryResponseDTO::fromEntity);
    }

    @Override
    public Optional<CountryResponseDTO> update(Long id, CountryRequestDTO countryRequestDTO) {

        return countryService
                .update(id, countryRequestDTO.name(), countryRequestDTO.continent())
                .map(CountryResponseDTO::fromEntity);
    }

    @Override
    public boolean deleteById(Long id) {
        return countryService.deleteById(id);
    }

    @Override
    public Optional<NumAuthorsByCountryResponseDTO> numAuthorsByCountry(Long id) {
        return countryService.numAuthorsByCountry(id).map(NumAuthorsByCountryResponseDTO::fromEntity);
    }

}
