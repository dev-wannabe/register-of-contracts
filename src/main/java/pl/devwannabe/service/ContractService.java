package pl.devwannabe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.devwannabe.domain.Model.ContractEntity;
import pl.devwannabe.domain.repository.ContractRepository;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public static void printBlue(Object input) {
        System.out.println("\u001B[34m" + input + "\u001B[0m");
    }

    public Page<ContractEntity> getAllContracts(PageRequest pageRequest) {
        Page<ContractEntity> allContracts = contractRepository.findAll(pageRequest);
        return allContracts;
    }

    public Page<ContractEntity> getByActive(boolean active, PageRequest pageRequest) {
        Page<ContractEntity> activeContracts = contractRepository.findByActive(active, pageRequest);
        return activeContracts;
    }

    public ContractEntity getByContractNumber(String ContractNumber) {
        return contractRepository.findByNumber(ContractNumber);
    }

    public void save(ContractEntity contractEntity) {
        contractRepository.save(contractEntity);
    }

    public void deleteById(Long id) {
        contractRepository.deleteById(id);
    }

    public ContractEntity getOne(Long id) {
        return contractRepository.getOne(id);
    }

}