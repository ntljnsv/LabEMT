package mk.finki.ukim.lab.dto;

import mk.finki.ukim.lab.model.views.NumBooksByAuthor;

public record NumBooksByAuthorResponseDTO(Long authorId, Integer numBooks) {

    public static NumBooksByAuthorResponseDTO fromEntity(NumBooksByAuthor numBooksByAuthor) {

        return new NumBooksByAuthorResponseDTO(
                numBooksByAuthor.getAuthorId(),
                numBooksByAuthor.getNumBooks()
        );
    }
}
