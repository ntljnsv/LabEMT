package mk.finki.ukim.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.lab.dto.BookInventoryResponseDTO;
import mk.finki.ukim.lab.dto.BookRequestDTO;
import mk.finki.ukim.lab.dto.BookResponseDTO;
import mk.finki.ukim.lab.model.exceptions.AuthorNotFoundException;
import mk.finki.ukim.lab.model.exceptions.BookNotFoundException;
import mk.finki.ukim.lab.model.exceptions.NoAvailableCopiesException;
import mk.finki.ukim.lab.service.application.BookApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/swagger-ui/index.html#/

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book API", description = "Endpoints for managing books")
public class BookController {

    private final BookApplicationService bookService;

    public BookController(BookApplicationService bookService) {
        this.bookService = bookService;
    }

    @Operation(
            summary = "Get all books", description = "Retrieves a list of all books."
    )
    @GetMapping
    public List<BookResponseDTO> listAll() {
        return bookService.listAll();
    }


    @Operation(summary = "Get book by ID", description = "Finds a book by it's ID.")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> findById(@PathVariable Long id) {
        try {
            return bookService.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (BookNotFoundException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Create new book", description = "Creates a new book.")
    @PostMapping("/add")
    public ResponseEntity<BookResponseDTO> create(@RequestBody BookRequestDTO bookRequestDTO) {
        try {
            return bookService.create(bookRequestDTO)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (AuthorNotFoundException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Update existing book", description = "Updates a book by it's ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<BookResponseDTO> update(@PathVariable Long id,
                                                 @RequestBody BookRequestDTO bookRequestDTO) {
        try {
            return bookService.update(id, bookRequestDTO)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (BookNotFoundException | AuthorNotFoundException exception) {
            return ResponseEntity.badRequest().build();
        }

    }

    @Operation(summary = "Delete book", description = "Deletes a book by it's ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(bookService.deleteById(id));
        } catch (BookNotFoundException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Borrow book", description = "Borrows a book by it's ID.")
    @PostMapping("/borrow-book/{id}")
    public ResponseEntity<BookInventoryResponseDTO> borrowBook(@PathVariable Long id) {
        try {
            return bookService.borrowBook(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
        } catch (BookNotFoundException | NoAvailableCopiesException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Return book", description = "Returns a book by it's ID.")
    @PostMapping("/return-book/{id}")
    public ResponseEntity<BookInventoryResponseDTO> returnBook(@PathVariable Long id) {
        try {
            return bookService.returnBook(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (BookNotFoundException | NoAvailableCopiesException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Search book by name or author name",
            description = "Find a book by name or it's authors name.")
    @GetMapping("/search")
    public List<BookResponseDTO> findByNameOrAuthor(@RequestParam String query) {
        return bookService.findByNameOrAuthor(query);
    }

}
