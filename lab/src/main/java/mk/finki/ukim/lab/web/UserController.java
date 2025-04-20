package mk.finki.ukim.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.lab.dto.*;
import mk.finki.ukim.lab.model.api_exception.ApiError;
import mk.finki.ukim.lab.model.domain.User;
import mk.finki.ukim.lab.model.exceptions.*;
import mk.finki.ukim.lab.service.application.UserApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @Operation(summary = "User login", description = "Authenticates a user and generates a JWT")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User authenticated successfully"
            ), @ApiResponse(responseCode = "404", description = "Invalid username or password")}
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLoginDTO loginUserDto) {
        try {
            return userApplicationService.login(loginUserDto)
                    .map(ResponseEntity::ok)
                    .orElseThrow(InvalidUserCredentialsException::new);
        } catch (InvalidUserCredentialsException e) {
            return ResponseEntity.notFound().build();
        }
    }



    @Operation(summary = "Get all books in user wishlist",
            description = "Retrieves all of the books in a user's wishlist")
    @GetMapping("/wishlist")
    public ResponseEntity<UserWishlistResponseDTO> getUserWishlist(@AuthenticationPrincipal User user) {
        try {
            return userApplicationService.findUserWishlist(user.getUsername())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (BookNotFoundException | NoAvailableCopiesException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Add book to wishlist", description = "Adds a book to user's wishlist")
    @PostMapping("/wishlist/add")
    public ResponseEntity<?> addBookToWishlist(@AuthenticationPrincipal User user,
                                               @RequestParam Long bookId) throws ResponseStatusException {
        try {
            return userApplicationService.addBookToWishlist(new UserWishlistRequestDTO(user.getUsername(), bookId))
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (BookNotFoundException | NoAvailableCopiesException | BookAlreadyInWishlistException exception) {
            return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST, exception));
        }
    }

    @Operation(summary = "Remove book from wishlist", description = "Removes a book from the user's wishlist")
    @PostMapping("/wishlist/remove")
    public ResponseEntity<UserWishlistResponseDTO> removeBookFromWishlist(@AuthenticationPrincipal User user,
                                                                          @RequestParam Long bookId ) {
        try {
            UserWishlistRequestDTO dto =  new UserWishlistRequestDTO(user.getUsername(), bookId);
            return userApplicationService.removeBookFromWishlist(dto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (BookNotFoundException | NoAvailableCopiesException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Borrow book from wishlist", description = "Borrows a book from the user's wishlist")
    @PostMapping("/wishlist/borrow")
    public ResponseEntity<UserWishlistResponseDTO> borrow(@AuthenticationPrincipal User user,
                                                          @RequestParam Long bookId) {
        try {
            UserWishlistRequestDTO dto =  new UserWishlistRequestDTO(user.getUsername(), bookId);
            return userApplicationService.borrowBookFromWishlist(dto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (BookNotFoundException | NoAvailableCopiesException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Borrow all books from wishlist", description = "Borrows/Removes all of the books from a user's wishlist")
    @PostMapping("/wishlist/borrow-all")
    public ResponseEntity<UserWishlistResponseDTO> borrowAllBooksFromWishlist(@AuthenticationPrincipal User user) {
        try {
            return userApplicationService.borrowAllBooksFromWishlist(user.getUsername())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (BookNotFoundException | NoAvailableCopiesException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

}

