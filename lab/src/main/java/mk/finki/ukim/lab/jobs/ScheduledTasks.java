package mk.finki.ukim.lab.jobs;

import mk.finki.ukim.lab.service.domain.AuthorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final AuthorService authorService;

    public ScheduledTasks(AuthorService authorService) {

        this.authorService = authorService;
    }

    @Scheduled(cron = "0 0 * * *")
    public void refreshMaterializedView() {
        this.authorService.refreshNumBooksByAuthorView();
    }
}


