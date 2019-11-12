package pl.devwannabe.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.devwannabe.domain.Contract;
import pl.devwannabe.postgresql.contract.ContractEntity;
import pl.devwannabe.postgresql.contract.ContractRepository;

@Service
public class ContractServiceImpl {

    @Autowired
    private ContractRepository contractRepository;

    public static void printBlue(Object input) {
        System.out.println("\u001B[34m" + input + "\u001B[0m");
    }

    public Page<Contract> getAllContracts(PageRequest pageRequest) {
        val contractEntities = contractRepository.findAll(pageRequest);
        val contracts = contractEntities.map(ContractEntity::convertTo);
        return contracts;
    }

    public Page<Contract> getByActive(boolean active, PageRequest pageRequest) {
        val activeContractsEntities = contractRepository.findByActive(active, pageRequest);
        val activeContracts = activeContractsEntities.map(ContractEntity::convertTo);
        return activeContracts;
    }

    public Contract getByContractNumber(String ContractNumber) {
        if (contractRepository.findByNumber(ContractNumber) != null)
            return contractRepository.findByNumber(ContractNumber).convertTo();
        return null;
    }

    public void save(Contract contract) {
        val contractEntity = ContractEntity.convertFrom(contract);
        contractRepository.save(contractEntity);
    }

    public void deleteById(Long id) {
        contractRepository.deleteById(id);
    }

    public Contract getOne(Long id) {
        if (contractRepository.getOne(id) != null)
            return contractRepository.getOne(id).convertTo();
        return null;
    }

}