package pl.devwannabe.service.validation.date_validation;

import pl.devwannabe.service.contract.ContractServiceImpl;
import pl.devwannabe.service.validation.annotations.EndDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class EndDateValidator implements ConstraintValidator<EndDate, LocalDate> {

    @Override
    public void initialize(EndDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate endDate, ConstraintValidatorContext context) {
        ContractServiceImpl.printBlue("isValid method for @EndDate is running...");
        return getInputStartDate().isBefore(endDate);
    }

    private LocalDate getInputStartDate() {
        return InputStartDateContractSupplier.inputStartDate;
    }
}