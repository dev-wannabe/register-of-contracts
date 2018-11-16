package pl.devwannabe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.devwannabe.domain.Contract;
import pl.devwannabe.domain.ContractRepository;
import pl.devwannabe.validation.ContractValidator;

import javax.validation.Valid;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;


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

    public void save(@Valid Contract contract) {
        contractRepository.save(contract);
    }

    public void deleteById(Long id) {
        contractRepository.deleteById(id);
    }

    public Contract getOne(Long id) {
        return contractRepository.getOne(id);
    }

}