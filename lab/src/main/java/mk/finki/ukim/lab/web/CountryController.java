package mk.finki.ukim.lab.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import mk.finki.ukim.lab.dto.CountryRequestDTO;
import mk.finki.ukim.lab.dto.CountryResponseDTO;
import mk.finki.ukim.lab.model.exceptions.CountryNotFoundException;
import mk.finki.ukim.lab.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country API", description = "Endpoints for managing countries")
public class CountryController {

    private final CountryApplicationService countryService;

    public CountryController(CountryApplicationService countryService) {
        this.countryService = countryService;
    }

    @Operation(
            summary = "Get all countries", description = "Retrieves a list of all countries."
    )
    @GetMapping
    public List<CountryResponseDTO> listAll() {

        return countryService.listAll();
    }

    @Operation(summary = "Get country by ID", description = "Finds a country by it's ID.")
    @GetMapping("/{id}")
    public ResponseEntity<CountryResponseDTO> findById(@PathVariable Long id) {
        try {
            return countryService.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (CountryNotFoundException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Create new country", description = "Creates a new country.")
    @PostMapping("/add")
    public ResponseEntity<CountryResponseDTO> create(@RequestBody CountryRequestDTO countryRequestDTO) {

        return countryService.create(countryRequestDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update existing country", description = "Updates a country by it's ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<CountryResponseDTO> update(@PathVariable Long id,
                                                     @RequestBody CountryRequestDTO countryRequestDTO) {
        try {
            return countryService.update(id, countryRequestDTO)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (CountryNotFoundException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Delete country", description = "Deletes a country by it's ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(countryService.deleteById(id));
        } catch (CountryNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
