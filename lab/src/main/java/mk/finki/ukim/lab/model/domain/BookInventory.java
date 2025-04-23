package mk.finki.ukim.lab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@Table(name = "book_inventory")
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

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookInventory inventory = (BookInventory) obj;
        return Objects.equals(id, inventory.id);
    }

}
