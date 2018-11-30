package pl.devwannabe.validation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.devwannabe.services.ContractService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNumberValidator implements ConstraintValidator<Unique, String> {

    @Autowired
    private ContractService contractService;

    @Override
    public void initialize(Unique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext context) {

        ContractService.printBlue("isValid method for unique NUMBER running... number=" +
                number + " id=" + getInputId());


        if (contractService == null || contractService.getByContractNumber(number) == null) {
            return true;
        } else if (getInputId() != null && contractService != null && contractService.getByContractNumber(number) != null) {
            boolean isId = contractService.getByContractNumber(number).getId()
                    .equals(getInputId());
            boolean isName = contractService.getByContractNumber(number).getNumber()
                    .equals(contractService.getOne(getInputId()).getNumber());
            return isId && isName;
        }
        return false;
    }

    private Long getInputId() {
        return IdContractInputSupplier.InputId;
    }

}

/*
When edit:
if(idInput != null && idInput == idDb && nameInput == nameDb) {
OK!
}
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
*/