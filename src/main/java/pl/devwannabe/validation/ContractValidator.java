package pl.devwannabe.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import pl.devwannabe.domain.Contract;
import pl.devwannabe.services.ContractService;

public class ContractValidator {

    @Autowired
    private ContractService contractService;

    public static boolean errorsAround(Contract contract, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("************  There were errors  ***********");
            bindingResult.getAllErrors().forEach(error -> {
                        System.out.println(error.getObjectName() +
                                " " + error.getDefaultMessage());
                    }
            );
            return true;
        }
        return false;
    }

}