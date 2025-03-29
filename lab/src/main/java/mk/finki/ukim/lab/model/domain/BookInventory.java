package mk.finki.ukim.lab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class BookInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer availableCopies;

    @OneToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public BookInventory(Book book, Integer availableCopies) {
        this.book = book;
        this.availableCopies = availableCopies;
    }
}
