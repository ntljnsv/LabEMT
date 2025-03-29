package mk.finki.ukim.lab.service.domain;

import mk.finki.ukim.lab.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAll();
    Optional<Country> findById(Long id);
    Optional<Country> create(String name, String continent);
    Optional<Country> update(Long id, String name, String continent);
    boolean deleteById(Long id);
}
