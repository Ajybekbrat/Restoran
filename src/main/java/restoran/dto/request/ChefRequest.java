package restoran.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import restoran.validation.*;

import java.time.LocalDate;

@Getter
@Setter
public class ChefRequest {
    @NotBlank
    private String lastName;
    @NotBlank
    private String firstName;
@ChefAgeValidation
    private int age;
    @EmailValidation
    private String email;
    @PasswordValidation
    private String password;
    @PhoneNumberValidation
    private String phoneNumber;
    @ChefExperienceValidation
    private int experience;
}
