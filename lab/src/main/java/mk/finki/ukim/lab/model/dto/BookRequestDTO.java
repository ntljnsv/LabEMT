package mk.finki.ukim.lab.model.dto;

import mk.finki.ukim.lab.model.Author;
import mk.finki.ukim.lab.model.Book;
import mk.finki.ukim.lab.model.enums.Category;

public record BookRequestDTO (String name, String category, Long authorId, Integer availableCopies) {

    public Book toEntity(Category category, Author author) {
        return new Book(name, category, author, availableCopies);
    }
}
