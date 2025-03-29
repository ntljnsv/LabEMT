package mk.finki.ukim.lab.service.domain.impl;

import mk.finki.ukim.lab.model.domain.Book;
import mk.finki.ukim.lab.model.enums.Category;
import mk.finki.ukim.lab.model.exceptions.AuthorNotFoundException;
import mk.finki.ukim.lab.model.exceptions.BookNotFoundException;
import mk.finki.ukim.lab.model.exceptions.NoAvailableCopiesException;
import mk.finki.ukim.lab.repository.BookRepository;
import mk.finki.ukim.lab.service.domain.AuthorService;
import mk.finki.ukim.lab.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> listAll() {

        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {

        return Optional.of(bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Override
    public Optional<Book> create(String name, String categoryName, Long authorId, Integer availableCopies) {

        if(name.isEmpty() || categoryName.isEmpty() || authorId == null) {
            throw new IllegalArgumentException();
        }
        return Optional.of(
            bookRepository.save(
                new Book(
                    name, Category.valueOf(categoryName),
                    authorService.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId)),
                    availableCopies
                )
            )
        );
    }

    @Override
    public Optional<Book> update(Long id, String name, String category, Long authorId, Integer availableCopies) {

        Book book = findById(id).get();
        if(name != null && !name.isEmpty()) {
            book.setName(name);
        }
        if(category != null && !category.isEmpty()) {
            book.setCategory(Category.valueOf(category.toUpperCase()));
        }
        if(authorId != null) {
            book.setAuthor(authorService.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId)));
        }
        if(availableCopies != null) {
            book.setAvailableCopies(availableCopies);
        }
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public boolean deleteById(Long id) {

        Optional<Book> book = findById(id);
        if(book.isPresent()) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Book> findByNameOrAuthor(String query) {
        return bookRepository.findByNameContainingOrAuthor_NameOrAuthor_Surname(query, query, query);
    }


    @Override
    public Optional<Book> borrowBook(Long id) {

        Book book = findById(id).get();
        if(book.getAvailableCopies() <= 0) {
            throw new NoAvailableCopiesException(id);
        }
        book.setAvailableCopies(book.getAvailableCopies()-1);
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> returnBook(Long id) {

        Book book = findById(id).get();
        book.setAvailableCopies(book.getAvailableCopies()+1);
        return Optional.of(bookRepository.save(book));
    }

}
