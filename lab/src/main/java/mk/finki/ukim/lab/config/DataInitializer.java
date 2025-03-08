package mk.finki.ukim.lab.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.lab.model.Author;
import mk.finki.ukim.lab.model.Book;
import mk.finki.ukim.lab.model.Country;
import mk.finki.ukim.lab.model.enums.Category;
import mk.finki.ukim.lab.repository.AuthorRepository;
import mk.finki.ukim.lab.repository.BookRepository;
import mk.finki.ukim.lab.repository.CountryRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init() {
        Country country1 = new Country("Country 1", "Continent 1");
        Country country2 = new Country("Country 2", "Continent 2");
        countryRepository.save(country1);
        countryRepository.save(country2);

        Author author1 = new Author("FirstName1", "LastName1", country1);
        Author author2 = new Author("FirstName2", "LastName2", country2);
        authorRepository.save(author1);
        authorRepository.save(author2);

        bookRepository.save(new Book("Book1", Category.BIOGRAPHY, author1, 5));
        bookRepository.save(new Book("Book2", Category.THRILER, author2, 1));
        bookRepository.save(new Book("Book3", Category.HISTORY, author1, 0));
    }
}
