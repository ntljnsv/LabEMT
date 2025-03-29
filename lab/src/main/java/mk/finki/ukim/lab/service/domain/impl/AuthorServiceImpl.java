package mk.finki.ukim.lab.service.domain.impl;

import mk.finki.ukim.lab.model.domain.Author;
import mk.finki.ukim.lab.model.domain.Country;
import mk.finki.ukim.lab.model.exceptions.AuthorNotFoundException;
import mk.finki.ukim.lab.model.exceptions.CountryNotFoundException;
import mk.finki.ukim.lab.repository.AuthorRepository;
import mk.finki.ukim.lab.service.domain.AuthorService;
import mk.finki.ukim.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> listAll() {

        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {

        return Optional.of(authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id)));
    }

    @Override
    public Optional<Author> create(String name, String surname, Long countryId) {

        return Optional.of(
            authorRepository.save(
                new Author(
                    name, surname,
                    countryService.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId))
                )
            )
        );
    }

    @Override
    public Optional<Author> update(Long id, String name, String surname, Long countryId) {

        Author author = findById(id).get();
        if(!name.isEmpty()) {
            author.setName(name);
        }
        if(surname != null && !surname.isEmpty()) {
            author.setSurname(surname);
        }
        if(countryId != null) {
            Country country = countryService.findById(countryId).orElseThrow(() -> new CountryNotFoundException(id));
            author.setCountry(country);
        }
        return Optional.of(
                authorRepository.save(author)
        );
    }

    @Override
    public boolean deleteById(Long id) {

        Optional<Author> author = findById(id);
        if(author.isPresent()) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
