package restoran.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import restoran.entity.enums.Role;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String lastName;
    private String firstName;
    private int age;
    private String email;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    private int experience;
}
