package mk.finki.ukim.lab.service.application;

import mk.finki.ukim.lab.dto.BookInventoryResponseDTO;
import mk.finki.ukim.lab.dto.BookRequestDTO;
import mk.finki.ukim.lab.dto.BookResponseDTO;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {

    List<BookResponseDTO> listAll();

    Optional<BookResponseDTO> findById(Long id);

    Optional<BookResponseDTO> create(BookRequestDTO bookRequestDTO);

    Optional<BookResponseDTO> update(Long id, BookRequestDTO bookRequestDTO);

    boolean deleteById(Long id);

    Optional<BookInventoryResponseDTO> borrowBook(Long id);

    Optional<BookInventoryResponseDTO> returnBook(Long id);

    List<BookResponseDTO> findByNameOrAuthor(String query);
}
