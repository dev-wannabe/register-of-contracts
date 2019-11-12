package pl.devwannabe.postgresql.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.devwannabe.service.ContractServiceImpl;

import javax.annotation.PostConstruct;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class ContractEntityListener {

    static private ContractServiceImpl contractService;

    @Autowired
    @Qualifier("contractService")
    public void setSearchService(ContractServiceImpl contractService) {
        this.contractService = contractService;
    }

    @PostConstruct
    public void init() {
        ContractServiceImpl.printBlue("contractService is null? " + (contractService == null));
    }

    @PreUpdate
    @PrePersist
    void preUpdate(ContractEntity contractEntity) {
        if(contractEntity != null) {
            contractEntity.setDaysLeft(calculateDaysLeft(contractEntity));
            contractEntity.setActive(isActive(contractEntity));
        }
    }

    @PostLoad
    void postLoad(ContractEntity contractEntity) {
        if(contractEntity != null) {
            contractEntity.setDaysLeft(calculateDaysLeft(contractEntity));
            contractEntity.setActive(isActive(contractEntity));
            contractService.save(contractEntity.convertTo());
        }
    }

    private int calculateDaysLeft(ContractEntity contractEntity) {
        if (LocalDate.now().isBefore(contractEntity.getStartDate())) {
            Long days = DAYS.between(contractEntity.getStartDate(), contractEntity.getEndDate());
            return days.intValue();
        } else {
            Long days = DAYS.between(LocalDate.now(), contractEntity.getEndDate());
            return days.intValue();
        }
    }

    private boolean isActive(ContractEntity contractEntity) {
        return LocalDate.now().isBefore(contractEntity.getEndDate().plusDays(1)) &&
                LocalDate.now().isAfter(contractEntity.getStartDate().minusDays(1));

    }

}