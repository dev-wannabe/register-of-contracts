package pl.devwannabe.service.validation.number_validation;
import org.springframework.stereotype.Component;
import pl.devwannabe.service.validation.annotations.Unique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class InputIdContractSupplier implements ConstraintValidator<Unique, Long> {

    protected static Long InputId;

    @Override
    public void initialize(Unique constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        InputId = id;
        return true;
    }



}