package restoran.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;
import restoran.validation.vali.ChefAgeVali;
import restoran.validation.vali.WaiterValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
@Documented
@Constraint(validatedBy = {ChefAgeVali.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface ChefAgeValidation {
    @Email
    String message() default "{chef age must be from 25 and 40}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}