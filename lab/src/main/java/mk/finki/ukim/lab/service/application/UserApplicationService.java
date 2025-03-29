package mk.finki.ukim.lab.service.application;

import mk.finki.ukim.lab.dto.UserCreateDTO;
import mk.finki.ukim.lab.dto.UserLoginDTO;
import mk.finki.ukim.lab.dto.UserResponseDTO;

import java.util.Optional;

public interface UserApplicationService {

    Optional<UserResponseDTO> register(UserCreateDTO createUserDto);

    Optional<UserResponseDTO> login(UserLoginDTO loginUserDto);

    Optional<UserResponseDTO> findByUsername(String username);

}
