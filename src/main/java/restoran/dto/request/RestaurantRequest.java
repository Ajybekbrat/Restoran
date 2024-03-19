package restoran.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String location;
    @NotBlank
    private String restType;
    @NotBlank
    private int numberOfEmployees;
    @NotBlank
    private int service;
}
