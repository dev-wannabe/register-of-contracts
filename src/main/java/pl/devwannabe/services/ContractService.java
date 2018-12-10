package pl.devwannabe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.devwannabe.domain.Model.Contract;
import pl.devwannabe.domain.repository.ContractRepository;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public static void printBlue(Object input) {
        System.out.println("\u001B[34m" + input + "\u001B[0m");
    }

    public Page<Contract> getAllContracts(PageRequest pageRequest) {
        Page<Contract> allContracts = contractRepository.findAll(pageRequest);
        return allContracts;
    }

    public Page<Contract> getByActive(boolean active, PageRequest pageRequest) {
        Page<Contract> activeContracts = contractRepository.findByActive(active, pageRequest);
        return activeContracts;
    }

    public Contract getByContractNumber(String ContractNumber) {
        return contractRepository.findByNumber(ContractNumber);
    }

    public void save(Contract contract) {
        contractRepository.save(contract);
    }

    public void deleteById(Long id) {
        contractRepository.deleteById(id);
    }

    public Contract getOne(Long id) {
        return contractRepository.getOne(id);
    }

}