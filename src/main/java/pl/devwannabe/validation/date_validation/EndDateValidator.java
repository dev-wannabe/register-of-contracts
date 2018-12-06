package pl.devwannabe.validation.date_validation;

import pl.devwannabe.services.ContractService;
import pl.devwannabe.validation.annotations.EndDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class EndDateValidator implements ConstraintValidator<EndDate, LocalDate> {

    @Override
    public void initialize(EndDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate endDate, ConstraintValidatorContext context) {
        ContractService.printBlue("isValid method for @EndDate is running...");
        return getInputStartDate().isBefore(endDate);
    }

    private LocalDate getInputStartDate() {
        return InputStartDateContractSupplier.inputStartDate;
    }
}