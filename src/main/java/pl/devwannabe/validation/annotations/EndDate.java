package pl.devwannabe.validation.annotations;

import pl.devwannabe.validation.date_validation.EndDateValidator;
import pl.devwannabe.validation.date_validation.InputStartDateContractSupplier;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {EndDateValidator.class})
public @interface EndDate {

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}