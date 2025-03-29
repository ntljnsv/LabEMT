package mk.finki.ukim.lab.dto;

import mk.finki.ukim.lab.model.domain.BookInventory;

public record BookInventoryResponseDTO (Long id, Long bookId, String bookName, int availableCopies){

    public static BookInventoryResponseDTO fromEntity(BookInventory bookInventory) {

        return new BookInventoryResponseDTO(
                bookInventory.getId(),
                bookInventory.getBook().getId(),
                bookInventory.getBook().getName(),
                bookInventory.getAvailableCopies()
        );
    }
}
