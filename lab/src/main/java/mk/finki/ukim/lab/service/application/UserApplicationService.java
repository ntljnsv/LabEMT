package mk.finki.ukim.lab.service.application;

import mk.finki.ukim.lab.dto.*;

import java.util.Optional;

public interface UserApplicationService {

    Optional<UserResponseDTO> register(UserCreateDTO createUserDto);

    Optional<UserResponseDTO> login(UserLoginDTO loginUserDto);

    Optional<UserResponseDTO> findByUsername(String username);

    Optional<UserWishlistResponseDTO> findUserWishlist(String username);

    Optional<UserWishlistResponseDTO> addBookToWishlist(UserWishlistRequestDTO userWishlistRequestDTO);

    Optional<UserWishlistResponseDTO> removeBookFromWishlist(UserWishlistRequestDTO userWishlistRequestDTO);

    Optional<UserWishlistResponseDTO> borrowBookFromWishlist(UserWishlistRequestDTO userWishlistRequestDTO);

    Optional<UserWishlistResponseDTO> borrowAllBooksFromWishlist(String username);
}
