package pl.devwannabe.validation.number_validation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.devwannabe.service.ContractServiceImpl;
import pl.devwannabe.validation.annotations.Unique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNumberValidator implements ConstraintValidator<Unique, String> {

    @Autowired
    private ContractServiceImpl contractService;

    @Override
    public void initialize(Unique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext context) {

        if (contractService == null || contractService.getByContractNumber(number) == null) {
            return true;
        } else if (getInputId() != null && contractService != null && contractService.getByContractNumber(number) != null) {
            boolean isIdEqual = contractService.getByContractNumber(number).getId()
                    .equals(getInputId());
            boolean isNumberEqual = contractService.getByContractNumber(number).getNumber()
                    .equals(contractService.getOne(getInputId()).getNumber());
            return isIdEqual && isNumberEqual;
        }
        return false;
    }

    private Long getInputId() {
        return InputIdContractSupplier.InputId;
    }

}