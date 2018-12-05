package pl.devwannabe.validation.annotations;

import pl.devwannabe.validation.number_validation.InputIdContractSupplier;
import pl.devwannabe.validation.number_validation.UniqueNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {InputIdContractSupplier.class, UniqueNumberValidator.class})
public @interface Unique {

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}