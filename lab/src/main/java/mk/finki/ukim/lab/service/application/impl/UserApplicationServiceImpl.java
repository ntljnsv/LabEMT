package mk.finki.ukim.lab.service.application.impl;

import mk.finki.ukim.lab.dto.*;
import mk.finki.ukim.lab.service.application.UserApplicationService;
import mk.finki.ukim.lab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<UserResponseDTO> register(UserCreateDTO userCreateDTO) {

        return userService.register(
                userCreateDTO.username(),
                userCreateDTO.password(),
                userCreateDTO.repeatPassword(),
                userCreateDTO.name(),
                userCreateDTO.surname(),
                userCreateDTO.role()
        ).map(UserResponseDTO::fromEntity);
    }

    @Override
    public Optional<UserResponseDTO> login(UserLoginDTO userLoginDTO) {

        return userService.login(
                userLoginDTO.username(),
                userLoginDTO.password()
        ).map(UserResponseDTO::fromEntity);
    }

    @Override
    public Optional<UserResponseDTO> findByUsername(String username) {

        return userService.findByUsername(username)
                .map(UserResponseDTO::fromEntity);
    }

    @Override
    public Optional<UserWishlistResponseDTO> findUserWishlist(String username) {
        return userService.findByUsername(username)
                .map(UserWishlistResponseDTO::fromEntity);
    }

    @Override
    public Optional<UserWishlistResponseDTO> addBookToWishlist(UserWishlistRequestDTO userWishlistRequestDTO) {

        return userService
                .addBookToWishlist(userWishlistRequestDTO.username(), userWishlistRequestDTO.bookId())
                .map(UserWishlistResponseDTO::fromEntity);
    }

    @Override
    public Optional<UserWishlistResponseDTO> removeBookFromWishlist(UserWishlistRequestDTO userWishlistRequestDTO) {

        return userService.removeBookFromWishlist(userWishlistRequestDTO.username(), userWishlistRequestDTO.bookId())
                .map(UserWishlistResponseDTO::fromEntity);
    }

    @Override
    public Optional<UserWishlistResponseDTO> borrowBookFromWishlist(UserWishlistRequestDTO userWishlistRequestDTO) {

        return userService.borrowBookFromWishlist(userWishlistRequestDTO.username(), userWishlistRequestDTO.bookId())
                .map(UserWishlistResponseDTO::fromEntity);
    }


    @Override
    public Optional<UserWishlistResponseDTO> borrowAllBooksFromWishlist(String username) {

        return userService.borrowAllBooksFromWishlist(username)
                .map(UserWishlistResponseDTO::fromEntity);
    }
}
