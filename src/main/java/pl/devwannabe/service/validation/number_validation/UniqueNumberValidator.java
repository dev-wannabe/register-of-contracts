package pl.devwannabe.service.validation.number_validation;

import lombok.NonNull;
import org.apache.commons.lang3.Validate;
import pl.devwannabe.domain.contract.ContractService;
import pl.devwannabe.service.validation.annotations.Unique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNumberValidator implements ConstraintValidator<Unique, String> {


    @NonNull
    private ContractService contractService;

    public UniqueNumberValidator(@NonNull ContractService contractService) {
        Validate.notNull(contractService);
        this.contractService = contractService;
    }

    @Override
    public void initialize(Unique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext context) {

        if (contractService == null || contractService.getContractByContractNumber(number) == null) {
            return true;
        } else if (getInputId() != null && contractService != null && contractService.getContractByContractNumber(number) != null) {
            boolean isIdEqual = contractService.getContractByContractNumber(number).getId()
                    .equals(getInputId());
            boolean isNumberEqual = contractService.getContractByContractNumber(number).getNumber()
                    .equals(contractService.getOneContract(getInputId()).getNumber());
            return isIdEqual && isNumberEqual;
        }
        return false;
    }

    private Long getInputId() {
        return InputIdContractSupplier.InputId;
    }

}