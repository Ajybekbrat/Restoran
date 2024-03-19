package restoran.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;
import restoran.entity.enums.Vegetarian;

import java.math.BigDecimal;

@Builder
public record MenuItemResponse(

        String name,
         String image,
         int price,
         String description,
         Vegetarian isVegetarian) {



}
