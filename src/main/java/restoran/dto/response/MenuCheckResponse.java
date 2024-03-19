package restoran.dto.response;

import restoran.entity.enums.Vegetarian;

public record MenuCheckResponse(
        String name,
        int price,
        String description,
        Vegetarian vegetarian
) {
}
