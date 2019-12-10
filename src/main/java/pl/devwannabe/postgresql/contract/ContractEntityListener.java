package pl.devwannabe.postgresql.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.devwannabe.service.contract.ContractServiceImpl;

import javax.annotation.PostConstruct;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class ContractEntityListener {

    private static SqlContractRepository sqlContractRepository;

    @Autowired
    public void setSearch(SqlContractRepository sqlContractRepository) {
        this.sqlContractRepository = sqlContractRepository;
    }

    @PostConstruct
    public void init() {
        ContractServiceImpl.printBlue("sqlContractRepository is null? " + (sqlContractRepository == null));
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
            sqlContractRepository.saveContract(contractEntity.convertTo());
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