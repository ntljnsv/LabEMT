package mk.finki.ukim.lab.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.lab.service.domain.AuthorService;
import mk.finki.ukim.lab.service.domain.CountryService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class RefreshViews {

    private final CountryService countryService;
    private final AuthorService authorService;

    public RefreshViews(CountryService countryService, AuthorService authorService) {
        this.countryService = countryService;
        this.authorService = authorService;
    }

    @PostConstruct
    public void init() {
        countryService.refreshNumAuthorsByCountryView();
        authorService.refreshNumBooksByAuthorView();
    }
}
