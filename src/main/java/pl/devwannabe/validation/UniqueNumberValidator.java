package pl.devwannabe.validation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.devwannabe.services.ContractService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNumberValidator implements ConstraintValidator<UniqueNumber, String> {

    @Autowired
    private ContractService contractService;

    @Override
    public void initialize(UniqueNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String contractNumber, ConstraintValidatorContext context) {
        if (contractService == null) {
            return true;
        } else {
            System.out.println("----------------<<<<<<<<<<<<---------@");
        }
        return contractService.getByContractNumber(contractNumber) == null;
    }

}