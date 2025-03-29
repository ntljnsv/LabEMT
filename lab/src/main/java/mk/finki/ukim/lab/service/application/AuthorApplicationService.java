package mk.finki.ukim.lab.service.application;

import mk.finki.ukim.lab.dto.AuthorRequestDTO;
import mk.finki.ukim.lab.dto.AuthorResponseDTO;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    List<AuthorResponseDTO> listAll();
    Optional<AuthorResponseDTO> findById(Long id);
    Optional<AuthorResponseDTO> create(AuthorRequestDTO authorRequestDTO);
    Optional<AuthorResponseDTO> update(Long id, AuthorRequestDTO authorRequestDTO);
    boolean deleteById(Long id);
}
