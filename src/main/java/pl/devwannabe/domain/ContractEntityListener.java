package pl.devwannabe.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.devwannabe.domain.Model.Contract;
import pl.devwannabe.services.ContractService;

import javax.annotation.PostConstruct;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class ContractEntityListener {

    static private ContractService contractService;

    @Autowired
    @Qualifier("contractService")
    public void setSearchService(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostConstruct
    public void init() {
        ContractService.printBlue("contractService is null? " + (contractService == null));
    }

    @PreUpdate
    @PrePersist
    void preUpdate(Contract contract) {
        contract.setDaysLeft(calculateDaysLeft(contract));
        contract.setActive(isActive(contract));
    }

    @PostLoad
    void postLoad(Contract contract) {
        contract.setDaysLeft(calculateDaysLeft(contract));
        contract.setActive(isActive(contract));
        contractService.save(contract);
    }

    private int calculateDaysLeft(Contract contract) {
        if (LocalDate.now().isBefore(contract.getStartDate())) {
            Long days = DAYS.between(contract.getStartDate(), contract.getEndDate());
            return days.intValue();
        } else {
            Long days = DAYS.between(LocalDate.now(), contract.getEndDate());
            return days.intValue();
        }
    }

    private boolean isActive(Contract contract) {
        return LocalDate.now().isBefore(contract.getEndDate().plusDays(1)) &&
                LocalDate.now().isAfter(contract.getStartDate().minusDays(1));

    }

}