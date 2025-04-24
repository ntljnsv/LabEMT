package mk.finki.ukim.lab.jobs;

import mk.finki.ukim.lab.service.domain.AuthorService;
import mk.finki.ukim.lab.service.domain.BookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final AuthorService authorService;
    private final BookService bookService;

    public ScheduledTasks(AuthorService authorService, BookService bookService) {

        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Scheduled(cron = "0 0 * * *")
    public void refreshMaterializedView() {
        this.authorService.refreshNumBooksByAuthorView();
    }

    @Scheduled(cron = "0 * * * *")
    public void refreshDamagedBooksView() {
        this.bookService.refreshDamagedBooksView();
    }
}


