package mk.finki.ukim.lab.service.application;

import mk.finki.ukim.lab.model.dto.BookRequestDTO;
import mk.finki.ukim.lab.model.dto.BookResponseDTO;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    List<BookResponseDTO> listAll();
    Optional<BookResponseDTO> findById(Long id);
    Optional<BookResponseDTO> create(BookRequestDTO bookRequestDTO);
    Optional<BookResponseDTO> update(Long id, BookRequestDTO bookRequestDTO);
    boolean deleteById(Long id);

    Optional<BookResponseDTO> borrowBook(Long id);
    Optional<BookResponseDTO> returnBook(Long id);

    List<BookResponseDTO> findByNameOrAuthor(String query);
}
