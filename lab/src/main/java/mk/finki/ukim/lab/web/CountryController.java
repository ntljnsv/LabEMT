package mk.finki.ukim.lab.web;

import mk.finki.ukim.lab.dto.CountryRequestDTO;
import mk.finki.ukim.lab.dto.CountryResponseDTO;
import mk.finki.ukim.lab.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryApplicationService countryService;

    public CountryController(CountryApplicationService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<CountryResponseDTO> listAll() {

        return countryService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponseDTO> findById(@PathVariable Long id) {

        return countryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<CountryResponseDTO> create(@RequestBody CountryRequestDTO countryRequestDTO) {

        return countryService.create(countryRequestDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CountryResponseDTO> update(@PathVariable Long id,
                                                     @RequestBody CountryRequestDTO countryRequestDTO) {

        return countryService.update(id, countryRequestDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        return ResponseEntity.ok(countryService.deleteById(id));
    }
}
