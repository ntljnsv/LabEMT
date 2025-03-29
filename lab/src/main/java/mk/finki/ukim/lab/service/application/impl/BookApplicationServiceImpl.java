package mk.finki.ukim.lab.service.application.impl;

import mk.finki.ukim.lab.dto.BookInventoryResponseDTO;
import mk.finki.ukim.lab.dto.BookRequestDTO;
import mk.finki.ukim.lab.dto.BookResponseDTO;
import mk.finki.ukim.lab.service.application.BookApplicationService;
import mk.finki.ukim.lab.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookService bookService;

    public BookApplicationServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public List<BookResponseDTO> listAll() {

        return bookService.listAll().stream().map(BookResponseDTO::fromEntity).toList();
    }

    @Override
    public Optional<BookResponseDTO> findById(Long id) {

        return bookService.findById(id).map(BookResponseDTO::fromEntity);
    }

    @Override
    public Optional<BookResponseDTO> create(BookRequestDTO bookRequestDTO) {

        return bookService
                .create(bookRequestDTO.name(), bookRequestDTO.category(),
                        bookRequestDTO.authorId(), bookRequestDTO.availableCopies())
                .map(BookResponseDTO::fromEntity);
    }

    @Override
    public Optional<BookResponseDTO> update(Long id, BookRequestDTO bookRequestDTO) {

        return bookService
                .update(id, bookRequestDTO.name(), bookRequestDTO.category(),
                        bookRequestDTO.authorId(), bookRequestDTO.availableCopies())
                .map(BookResponseDTO::fromEntity);
    }

    @Override
    public boolean deleteById(Long id) {
        return bookService.deleteById(id);
    }

    @Override
    public Optional<BookInventoryResponseDTO> borrowBook(Long id) {
        return bookService.borrowBook(id).map(BookInventoryResponseDTO::fromEntity);
    }

    @Override
    public Optional<BookInventoryResponseDTO> returnBook(Long id) {
        return bookService.returnBook(id).map(BookInventoryResponseDTO::fromEntity);
    }

    @Override
    public List<BookResponseDTO> findByNameOrAuthor(String query) {
        return bookService.findByNameOrAuthor(query)
                .stream().map(BookResponseDTO::fromEntity).toList();
    }

}
