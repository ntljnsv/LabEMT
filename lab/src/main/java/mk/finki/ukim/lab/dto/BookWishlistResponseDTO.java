package mk.finki.ukim.lab.dto;

import mk.finki.ukim.lab.model.domain.Book;

public record BookWishlistResponseDTO(Long id, String name) {

    public static BookWishlistResponseDTO fromEntity(Book book) {
        return new BookWishlistResponseDTO(book.getId(), book.getName());
    }
}
