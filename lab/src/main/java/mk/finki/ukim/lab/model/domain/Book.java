package mk.finki.ukim.lab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.finki.ukim.lab.model.enums.Category;

@Data
@Entity
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Author author;

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    private BookInventory inventory;

    public Book(String name, Category category, Author author) {
        this.name = name;
        this.category = category;
        this.author = author;
    }
}
