package mk.finki.ukim.lab.service.domain;

import mk.finki.ukim.lab.model.domain.Author;
import mk.finki.ukim.lab.model.projections.AuthorProjection;
import mk.finki.ukim.lab.model.views.NumBooksByAuthor;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> listAll();

    Optional<Author> findById(Long id);

    Optional<Author> create(String name, String surname, Long countryId);

    Optional<Author> update(Long id, String name, String surname, Long countryId);

    boolean deleteById(Long id);

    void refreshNumBooksByAuthorView();

    Optional<NumBooksByAuthor> numBooksByAuthor(Long id);

    List<AuthorProjection> listAllAuthorNames();
}
