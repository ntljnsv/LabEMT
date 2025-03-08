package mk.finki.ukim.lab.model.dto;

import mk.finki.ukim.lab.model.Book;

public record BookResponseDTO (Long id, String name, String category, Long authorId, Integer availableCopies) {

    public static BookResponseDTO fromEntity(Book book) {
        return new BookResponseDTO(
                book.getId(),
                book.getName(),
                book.getCategory().name(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }
}
