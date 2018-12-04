package pl.devwannabe.validation;
import org.springframework.stereotype.Component;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class IdContractInputSupplier implements ConstraintValidator<Unique, Long> {

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