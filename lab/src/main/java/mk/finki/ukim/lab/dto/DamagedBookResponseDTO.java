package mk.finki.ukim.lab.dto;

import mk.finki.ukim.lab.model.views.DamagedBook;

public record DamagedBookResponseDTO(Long id, String name, String condition) {

    public static DamagedBookResponseDTO fromEntity(DamagedBook damagedBook) {
        return new DamagedBookResponseDTO(
                damagedBook.getId(),
                damagedBook.getName(),
                damagedBook.getBookCondition().toString()
        );
    }
}
