package pl.devwannabe.validation.date_validation;

import pl.devwannabe.validation.annotations.StartDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class InputStartDateContractSupplier implements ConstraintValidator <StartDate, LocalDate> {

    protected static LocalDate inputStartDate;

    @Override
    public void initialize(StartDate constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocalDate startDate, ConstraintValidatorContext context) {
        inputStartDate = startDate;
        return true;
    }
}
