package mk.finki.ukim.lab.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.lab.model.domain.Author;
import mk.finki.ukim.lab.model.domain.Book;
import mk.finki.ukim.lab.model.domain.Country;
import mk.finki.ukim.lab.model.domain.User;
import mk.finki.ukim.lab.model.enums.Category;
import mk.finki.ukim.lab.model.enums.Role;
import mk.finki.ukim.lab.repository.AuthorRepository;
import mk.finki.ukim.lab.repository.BookRepository;
import mk.finki.ukim.lab.repository.CountryRepository;
import mk.finki.ukim.lab.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            BookRepository bookRepository,
            AuthorRepository authorRepository,
            CountryRepository countryRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "Name",
                "Surname",
                Role.ROLE_USER
        ));

        userRepository.save(new User(
                "lib",
                passwordEncoder.encode("lib"),
                "LibrarianName",
                "LibrarianSurname",
                Role.ROLE_LIBRARIAN
        ));
    }
}
