package mk.finki.ukim.lab.dto;

import mk.finki.ukim.lab.model.domain.Book;

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
