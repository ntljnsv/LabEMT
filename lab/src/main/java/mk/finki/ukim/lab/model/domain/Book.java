package mk.finki.ukim.lab.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.finki.ukim.lab.model.enums.Category;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    @JsonIgnore
    private BookInventory inventory;

    @ManyToMany(mappedBy = "wishlist")
    private Set<User> usersWhoWishlisted = new HashSet<>();

    public Book(String name, Category category, Author author) {
        this.name = name;
        this.category = category;
        this.author = author;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return Objects.equals(id, book.id);
    }


}
