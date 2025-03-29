package mk.finki.ukim.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.lab.dto.*;
import mk.finki.ukim.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.lab.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.lab.model.exceptions.NoAvailableCopiesException;
import mk.finki.ukim.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.lab.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and registration") // Swagger tag
public class UserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User registered successfully"
            ), @ApiResponse(
                    responseCode = "400", description = "Invalid input or passwords do not match"
            )}
    )
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserCreateDTO userCreateDTO) {
        try {
            return userApplicationService.register(userCreateDTO)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User authenticated successfully"
            ), @ApiResponse(responseCode = "404", description = "Invalid username or password")}
    )
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(HttpServletRequest request) {
        try {
            UserResponseDTO userResponseDTO = userApplicationService.login(
                    new UserLoginDTO(request.getParameter("username"), request.getParameter("password"))
            ).orElseThrow(InvalidUserCredentialsException::new);

            request.getSession().setAttribute("user", userResponseDTO.toEntity());
            return ResponseEntity.ok(userResponseDTO);
        } catch (InvalidUserCredentialsException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "User logout", description = "Ends the user's session")
    @ApiResponse(responseCode = "200", description = "User logged out successfully")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }


    @Operation(summary = "Add book to wishlist", description = "Adds a book to user's wishlist")
    @PostMapping("/wishlist/add")
    public ResponseEntity<UserWishlistResponseDTO> addBookToWishlist(
            @RequestBody UserWishlistRequestDTO userWishlistRequestDTO) {
        try {
            return userApplicationService.addBookToWishlist(userWishlistRequestDTO)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (NoAvailableCopiesException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Remove book from wishlist", description = "Removes a book from the user's wishlist")
    @PostMapping("/wishlist/remove")
    public ResponseEntity<UserWishlistResponseDTO> removeBookFromWishlist(
            @RequestBody UserWishlistRequestDTO userWishlistRequestDTO) {
        try {
            return userApplicationService.removeBookFromWishlist(userWishlistRequestDTO)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (NoAvailableCopiesException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Borrow book from wishlist", description = "Borrows a book from the user's wishlist")
    @PostMapping("/wishlist/borrow")
    public ResponseEntity<UserWishlistResponseDTO> borrow(
            @RequestBody UserWishlistRequestDTO userWishlistRequestDTO) {
        try {
            return userApplicationService.borrowBookFromWishlist(userWishlistRequestDTO)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (NoAvailableCopiesException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Borrow all books from wishlist", description = "Borrows/Removes all of the books from a user's wishlist")
    @PostMapping("/wishlist/borrow-all/{username}")
    public ResponseEntity<UserWishlistResponseDTO> borrowAllBooksFromWishlist(
            @PathVariable String username) {
        try {
            return userApplicationService.borrowAllBooksFromWishlist(username)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (NoAvailableCopiesException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

}

