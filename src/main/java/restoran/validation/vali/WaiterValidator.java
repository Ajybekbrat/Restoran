package restoran.validation.vali;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import restoran.validation.WaiterValidation;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class WaiterValidator implements ConstraintValidator<WaiterValidation, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value  >=18 && 30>value ;
    }
}
