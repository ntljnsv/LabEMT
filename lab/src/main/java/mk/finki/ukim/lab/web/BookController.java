package mk.finki.ukim.lab.web;

import mk.finki.ukim.lab.model.dto.BookRequestDTO;
import mk.finki.ukim.lab.model.dto.BookResponseDTO;
import mk.finki.ukim.lab.service.application.BookApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/swagger-ui/index.html#/

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookApplicationService bookService;

    public BookController(BookApplicationService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponseDTO> listAll() {
        return bookService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> findById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<BookResponseDTO> create(@RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.create(bookRequestDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<BookResponseDTO> update(@PathVariable Long id,
                                                 @RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.update(id, bookRequestDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.deleteById(id));
    }

    @PostMapping("/borrow-book/{id}")
    public ResponseEntity<BookResponseDTO> borrowBook(@PathVariable Long id) {
        return bookService.borrowBook(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PostMapping("/return-book/{id}")
    public ResponseEntity<BookResponseDTO> returnBook(@PathVariable Long id) {
        return bookService.returnBook(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
