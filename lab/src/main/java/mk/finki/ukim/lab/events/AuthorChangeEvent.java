package mk.finki.ukim.lab.events;

import lombok.Getter;
import mk.finki.ukim.lab.model.domain.Author;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class AuthorChangeEvent extends ApplicationEvent{

    private final LocalDateTime when;
    private final String eventType;

    public AuthorChangeEvent(Author source, String eventType) {
        super(source);
        this.when = LocalDateTime.now();
        this.eventType = eventType;
    }

    public AuthorChangeEvent(Author source, LocalDateTime when, String eventType) {
        super(source);
        this.when = when;
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }
}
