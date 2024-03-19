package restoran.validation.vali;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import restoran.validation.NumberEmpValidation;

public class NumberEmployVali implements ConstraintValidator<NumberEmpValidation, Integer> {
@Override
public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value>=15;
        }
}
