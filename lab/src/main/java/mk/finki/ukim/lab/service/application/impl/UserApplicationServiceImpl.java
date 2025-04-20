package mk.finki.ukim.lab.service.application.impl;

import mk.finki.ukim.lab.dto.*;
import mk.finki.ukim.lab.model.domain.User;
import mk.finki.ukim.lab.security.JwtHelper;
import mk.finki.ukim.lab.service.application.UserApplicationService;
import mk.finki.ukim.lab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
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
    public Optional<LoginResponseDTO> login(UserLoginDTO userLoginDTO) {

        User user = userService.login(userLoginDTO.username(), userLoginDTO.password()).get();

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginResponseDTO(token));
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
