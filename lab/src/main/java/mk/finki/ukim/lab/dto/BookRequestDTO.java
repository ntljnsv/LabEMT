package mk.finki.ukim.lab.dto;

import mk.finki.ukim.lab.model.domain.Author;
import mk.finki.ukim.lab.model.domain.Book;
import mk.finki.ukim.lab.model.enums.Category;

public record BookRequestDTO (String name, String category, Long authorId, int availableCopies) {

    public Book toEntity(Category category, Author author) {
        return new Book(name, category, author);
    }
}
