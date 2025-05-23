package mk.finki.ukim.lab.listeners;

import mk.finki.ukim.lab.events.AuthorChangeEvent;
import mk.finki.ukim.lab.service.domain.CountryService;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class AuthorChangeEventListener {

    private final CountryService countryService;

    public AuthorChangeEventListener(CountryService countryService) {
        this.countryService = countryService;
    }

    @EventListener
    public void onAuthorChanged(AuthorChangeEvent event) {
        countryService.refreshNumAuthorsByCountryView();
    }

}
