package restoran.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import restoran.entity.enums.Vegetarian;

@Getter
@Setter
public class MenuItemRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String image;
    @PositiveOrZero
    private int price;
    @NotBlank
    private String description;
    @Enumerated(EnumType.STRING)
    private Vegetarian isVegetarian;
}
