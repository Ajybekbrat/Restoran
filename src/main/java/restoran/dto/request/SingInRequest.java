package restoran.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingInRequest {
    @NotBlank
    String email;
    @NotBlank
    String password;
}
