package restoran.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Builder
public record ChequeResponse(

        String firstName,
        List<MenuItemResponse>menuItemResponses,
        String service,
        int totalPrice) {
}
