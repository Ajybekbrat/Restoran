package restoran.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubCategoryRequest {
    @NotBlank
    private String name;
}
