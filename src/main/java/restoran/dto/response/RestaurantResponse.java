package restoran.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder

public record RestaurantResponse(
         Long id,
         String name,
         String location,
         String restType,
         int numberOfEmployees,
         int service
) {
}
