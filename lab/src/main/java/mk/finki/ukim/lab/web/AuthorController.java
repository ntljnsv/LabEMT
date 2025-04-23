package mk.finki.ukim.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.lab.dto.*;
import mk.finki.ukim.lab.model.exceptions.AuthorNotFoundException;
import mk.finki.ukim.lab.model.exceptions.CountryNotFoundException;
import mk.finki.ukim.lab.service.application.AuthorApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author API", description = "Endpoints for managing authors")
public class AuthorController {
    private final AuthorApplicationService authorService;

    public AuthorController(AuthorApplicationService authorService) {
        this.authorService = authorService;
    }

    @Operation(
            summary = "Get all authors", description = "Retrieves a list of all authors."
    )
    @GetMapping
    public List<AuthorResponseDTO> listAll() {

        return authorService.listAll();
    }

    @Operation(summary = "Get author by ID", description = "Finds an author by their ID.")
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> findById(@PathVariable Long id) {
        try {
            return authorService.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (AuthorNotFoundException exception) {
            return ResponseEntity.badRequest().build();
        }

    }

    @Operation(summary = "Create new author", description = "Creates a new author.")
    @PostMapping("/add")
    public ResponseEntity<AuthorResponseDTO> create(@RequestBody AuthorRequestDTO authorRequestDTO) {

        return authorService.create(authorRequestDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update existing author", description = "Updates an author by their ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<AuthorResponseDTO> update(@PathVariable Long id,
                                                    @RequestBody AuthorRequestDTO authorRequestDTO) {
        try {
            return authorService.update(id, authorRequestDTO)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (AuthorNotFoundException | CountryNotFoundException exception) {
            return ResponseEntity.badRequest().build();
        }

    }

    @Operation(summary = "Delete author", description = "Deletes an author by their ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        try {
            return  ResponseEntity.ok(authorService.deleteById(id));
        } catch (AuthorNotFoundException exception) {
            return ResponseEntity.badRequest().build();
        }

    }

    @Operation(
            summary = "View number of books by each author",
            description = "Returns number of books written by each author.")
    @GetMapping("/by-author")
    public ResponseEntity<NumBooksByAuthorResponseDTO> numBooksByAuthor(@RequestParam Long id) {
        try {
            return authorService.numBooksByAuthor(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Get all author names and surnames",
            description = "Retrieves a list of all author projections (name and surname).")
    @GetMapping("/names")
    public List<AuthorProjectionDTO> listAllAuthorNames() {
        return authorService.listAllAuthorNames();
    }

}
