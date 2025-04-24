package mk.finki.ukim.lab.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import mk.finki.ukim.lab.model.enums.BookCondition;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("select * from public.damaged_books")
@Immutable
public class DamagedBook {
    @Id
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private BookCondition bookCondition;
}
