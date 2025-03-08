package mk.finki.ukim.lab.service.domain.impl;

import mk.finki.ukim.lab.model.Country;
import mk.finki.ukim.lab.model.exceptions.CountryNotFoundException;
import mk.finki.ukim.lab.repository.CountryRepository;
import mk.finki.ukim.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {

        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {

        return Optional.of(countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id)));
    }

    @Override
    public Optional<Country> create(String name, String continent) {

        return Optional.of(
                countryRepository.save(new Country(name, continent))
        );
    }

    @Override
    public Optional<Country> update(Long id, String name, String continent) {

        Country country = findById(id).get();
        if(name != null && !name.isEmpty()) {
            country.setName(name);
        }
        if(continent != null && !continent.isEmpty()) {
            country.setContinent(continent);
        }
        return Optional.of(
                countryRepository.save(country)
        );
    }

    @Override
    public boolean deleteById(Long id) {

        Optional<Country> country = findById(id);
        if(country.isPresent()) {
            countryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
