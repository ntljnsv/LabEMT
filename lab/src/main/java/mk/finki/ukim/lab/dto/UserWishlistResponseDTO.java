package mk.finki.ukim.lab.dto;

import mk.finki.ukim.lab.model.domain.User;

import java.util.List;

public record UserWishlistResponseDTO(String username, List<BookWishlistResponseDTO> wishlist ) {

    public static UserWishlistResponseDTO fromEntity(User user) {
        return new UserWishlistResponseDTO(
                user.getUsername(),
                user.getWishlist().stream().map(BookWishlistResponseDTO::fromEntity).toList()
        );
    }

}
