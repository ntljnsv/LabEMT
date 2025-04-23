package mk.finki.ukim.lab.service.domain.impl;

import mk.finki.ukim.lab.events.AuthorChangeEvent;
import mk.finki.ukim.lab.model.domain.Author;
import mk.finki.ukim.lab.model.domain.Country;
import mk.finki.ukim.lab.model.exceptions.AuthorNotFoundException;
import mk.finki.ukim.lab.model.exceptions.CountryNotFoundException;
import mk.finki.ukim.lab.model.projections.AuthorProjection;
import mk.finki.ukim.lab.model.views.NumBooksByAuthor;
import mk.finki.ukim.lab.repository.AuthorRepository;
import mk.finki.ukim.lab.repository.NumBooksByAuthorRepository;
import mk.finki.ukim.lab.service.domain.AuthorService;
import mk.finki.ukim.lab.service.domain.CountryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;
    private final NumBooksByAuthorRepository numBooksByAuthorRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AuthorServiceImpl(
            AuthorRepository authorRepository,
            CountryService countryService,
            NumBooksByAuthorRepository numBooksByAuthorRepository,
            ApplicationEventPublisher applicationEventPublisher
    ) {

        this.authorRepository = authorRepository;
        this.countryService = countryService;
        this.numBooksByAuthorRepository = numBooksByAuthorRepository;
        this.applicationEventPublisher = applicationEventPublisher;
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

        Author author = authorRepository.save(
            new Author(
                name, surname,
                countryService.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId))
            )
        );

        this.applicationEventPublisher.publishEvent(new AuthorChangeEvent(author, "CREATE"));

        return Optional.of(author);
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

        this.applicationEventPublisher.publishEvent(new AuthorChangeEvent(author, "UPDATE"));

        return Optional.of(
                authorRepository.save(author)
        );
    }

    @Override
    public boolean deleteById(Long id) {

        Optional<Author> author = findById(id);
        if(author.isPresent()) {
            authorRepository.deleteById(id);

            this.applicationEventPublisher.publishEvent(new AuthorChangeEvent(author.get(), "DELETE"));

            return true;
        }
        return false;
    }

    @Override
    public void refreshNumBooksByAuthorView() {
        numBooksByAuthorRepository.refreshMaterializedView();
    }

    @Override
    public Optional<NumBooksByAuthor> numBooksByAuthor(Long id) {
        Optional<NumBooksByAuthor> numBooksByAuthor = numBooksByAuthorRepository.findByAuthorId(id);

        if(numBooksByAuthor.isEmpty()) {
            throw new AuthorNotFoundException(id);
        }

        return numBooksByAuthor;
    }


    @Override
    public List<AuthorProjection> listAllAuthorNames() {
        return authorRepository.authorProjection();
    }
}
