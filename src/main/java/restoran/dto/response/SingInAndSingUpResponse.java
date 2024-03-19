package restoran.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import restoran.entity.enums.Role;


@Getter
@Setter
@Builder
public class SingInAndSingUpResponse {
    String token;
    String email;
    String message;
    Role role;
    HttpStatus httpStatus;
}
