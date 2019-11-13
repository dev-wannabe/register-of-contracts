package pl.devwannabe.service;

import lombok.NonNull;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.devwannabe.domain.contract.Contract;
import pl.devwannabe.domain.contract.ContractRepository;

@Service
public class ContractServiceImpl implements pl.devwannabe.domain.contract.ContractService {

    @NonNull
    private final ContractRepository contractRepository;

    public ContractServiceImpl(@NonNull ContractRepository contractRepository) {
        Validate.notNull(contractRepository);
        this.contractRepository = contractRepository;
    }

    public static void printBlue(Object input) {
        System.out.println("\u001B[34m" + input + "\u001B[0m");
    }

    @Override
    public Page<Contract> getAllContracts(@NonNull PageRequest pageRequest) {
        Validate.notNull(pageRequest);
        return contractRepository.getAll(pageRequest);
    }

    @Override
    public Page<Contract> getActiveContracts(boolean active, @NonNull PageRequest pageRequest) {
        Validate.notNull(pageRequest);
        return contractRepository.getActive(active, pageRequest);
    }

    public Contract getContractByContractNumber(@NonNull String contractNumber) {
        Validate.notNull(contractNumber);
        return contractRepository.getByContractNumber(contractNumber);
    }

    @Override
    public void saveContract(@NonNull Contract contract) {
        Validate.notNull(contract);
        contractRepository.saveContract(contract);
    }

    @Override
    public void deleteContractById(@NonNull Long id) {
        Validate.notNull(id);
        contractRepository.deleteContract(id);
    }

    @Override
    public Contract getOneContract(@NonNull Long id) {
        Validate.notNull(id);
        return contractRepository.getContract(id);
    }

}