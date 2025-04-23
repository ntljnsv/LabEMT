package mk.finki.ukim.lab.service.domain;

import mk.finki.ukim.lab.model.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> listAll();

    Optional<Book> findById(Long id);

    Optional<Book> create(String name, String category, Long authorId, Integer availableCopies);

    Optional<Book> update(Long id, String name, String category, Long authorId, Integer availableCopies);

    boolean deleteById(Long id);

    boolean hasAvailableCopies(Long id);

    List<Book> findByNameOrAuthor(String query);


}
