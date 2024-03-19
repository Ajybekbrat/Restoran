package restoran.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import restoran.validation.*;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @WaiterValidation
    private int age;
    @EmailValidation
    private String email;
    @PasswordValidation
    private String password;
    @PhoneNumberValidation
    private String phoneNumber;
    @WaiterExperienceValidation
    private int experience;
}
