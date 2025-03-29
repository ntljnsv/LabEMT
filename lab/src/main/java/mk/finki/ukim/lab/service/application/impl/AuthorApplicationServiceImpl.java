package mk.finki.ukim.lab.service.application.impl;

import mk.finki.ukim.lab.dto.AuthorRequestDTO;
import mk.finki.ukim.lab.dto.AuthorResponseDTO;
import mk.finki.ukim.lab.service.application.AuthorApplicationService;
import mk.finki.ukim.lab.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private final AuthorService authorService;

    public AuthorApplicationServiceImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public List<AuthorResponseDTO> listAll() {

        return authorService.listAll().stream().map(AuthorResponseDTO::fromEntity).toList();
    }

    @Override
    public Optional<AuthorResponseDTO> findById(Long id) {

        return authorService.findById(id).map(AuthorResponseDTO::fromEntity);
    }

    @Override
    public Optional<AuthorResponseDTO> create(AuthorRequestDTO authorRequestDTO) {

        return authorService
                .create(authorRequestDTO.name(), authorRequestDTO.surname(), authorRequestDTO.countryId())
                .map(AuthorResponseDTO::fromEntity);
    }

    @Override
    public Optional<AuthorResponseDTO> update(Long id, AuthorRequestDTO authorRequestDTO) {

        return authorService
                .update(id, authorRequestDTO.name(), authorRequestDTO.surname(), authorRequestDTO.countryId())
                .map(AuthorResponseDTO::fromEntity);
    }

    @Override
    public boolean deleteById(Long id) {

        return authorService.deleteById(id);
    }
}
